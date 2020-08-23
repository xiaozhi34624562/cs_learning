import torch
import torch.nn as nn
import torchvision
from torchvision.transforms import transforms
from torch.utils.data import DataLoader, Dataset
from PIL import Image
from efficientnet_pytorch import EfficientNet

# single densenet121
class DenseNet121(nn.Module):
    def __init__(self, num_classes, is_trained=True):

        super().__init__()
        
        # Load the DenseNet121 from ImageNet
        self.net = torchvision.models.densenet121(pretrained=is_trained)
        
        # Get the input dimension of last layer
        kernel_count = self.net.classifier.in_features
        
        # Replace last layer with new layer that have num_classes nodes, after that apply Sigmoid to the output
        self.net.classifier = nn.Sequential(nn.Linear(kernel_count, num_classes), nn.Sigmoid())
        
    def forward(self, inputs):
        
        return self.net(inputs)




# densenet121 with dropout and p =0.9
class DenseNet121_with_dropout(nn.Module):
    def __init__(self, num_classes, is_trained=True):

        super().__init__()
        
        # Load the DenseNet121 from ImageNet
        self.net = torchvision.models.densenet121(pretrained=is_trained)
        
        # Get the input dimension of last layer
        kernel_count = self.net.classifier.in_features
        
        # Replace last layer with new layer that have num_classes nodes and add a dropout, after that apply Sigmoid to the output
        self.net.classifier = nn.Sequential(nn.Linear(kernel_count, 500), nn.Dropout(p=0.9), nn.Linear(500, num_classes), nn.Sigmoid())
        
    def forward(self, inputs):
        
        return self.net(inputs)

    
    
    
    
    
# this is our combined model
class Combined_model_1(nn.Module):
    def __init__(self, num_classes, is_trained=True):
        super().__init__()
        
        # Load the DenseNet121, DenseNet169, DenseNet201, vgg19, vgg19_bn, mnasnet1_0
        self.net_1 = torchvision.models.densenet121(pretrained=is_trained)
        self.net_2 = torchvision.models.densenet169(pretrained=is_trained)
        self.net_3 = torchvision.models.densenet201(pretrained=is_trained)
        self.net_4 = torchvision.models.mnasnet1_0(pretrained=is_trained)
        self.net_5 = torchvision.models.vgg19_bn(pretrained=is_trained)
        self.net_6 = torchvision.models.vgg19(pretrained=is_trained)
        
        # Get the input dimension of last layer
        kernel_count_1 = 1024
        kernel_count_2 = 1664
        kernel_count_3 = 1920
        kernel_count_4 = 1280
        kernel_count_5 = 25088
        kernel_count_6 = 25088
        
        #Remove last linear layer
        self.net_1.classifier = nn.Identity()
        self.net_2.classifier = nn.Identity()
        self.net_3.classifier = nn.Identity()
        self.net_4.classifier = nn.Identity()
        self.net_5.classifier = nn.Identity()
        self.net_6.classifier = nn.Identity()
        
        # Replace last layer with new layer that have num_classes nodes, after that apply Sigmoid to the output
        self.classifier = nn.Sequential(nn.Linear(kernel_count_1 + kernel_count_2 + kernel_count_3 +\
                                                      kernel_count_4 + kernel_count_5 + kernel_count_6, num_classes), nn.Sigmoid())
        
    def forward(self, x):
        x1 = self.net_1(x.clone())  
        x1 = x1.view(x1.size(0), -1)
        
        x2 = self.net_2(x.clone())  
        x2 = x2.view(x2.size(0), -1)
        
        x3 = self.net_3(x.clone())  
        x3 = x3.view(x3.size(0), -1)
        
        x4 = self.net_4(x.clone())  
        x4 = x4.view(x4.size(0), -1)
        
        x5 = self.net_5(x.clone())  
        x5 = x5.view(x5.size(0), -1)
        
        x6 = self.net_6(x)  
        x6 = x6.view(x6.size(0), -1)
        
        x_all = torch.cat((x1, x2, x3, x4, x5, x6), dim=1)
        

        return self.classifier(x_all)


    
    
    
    
# efficientnet b5 
class Efficientnet(nn.Module):
    
    def __init__(self):
        super().__init__()
        
        self.net = EfficientNet.from_pretrained('efficientnet-b5')
        self.net._fc = nn.Sequential(nn.Linear(2048, 14), nn.Sigmoid())
        
    def forward(self,x):
        
        return self.net(x)

    
    
    
    
    
# our second combined model
class Combined_model_2(nn.Module):
    def __init__(self, num_classes, is_trained=True):
        super().__init__()
        
        # Load the DenseNet121, DenseNet201, efficientnet b2 and b3
        self.net_1 = torchvision.models.densenet121(pretrained=is_trained)
        self.net_3 = torchvision.models.densenet201(pretrained=is_trained)
        self.net_4 = EfficientNet.from_pretrained('efficientnet-b2')
        self.net_5 = EfficientNet.from_pretrained('efficientnet-b3')
        
        # Get the input dimension of last layer
        kernel_count_1 = 1024
        kernel_count_3 = 1920
        kernel_count_4 = 1408
        kernel_count_5 = 1536
        
        #Remove last linear layer
        self.net_1.classifier = nn.Identity()
        self.net_3.classifier = nn.Identity()
        self.net_4._fc = nn.Identity()
        self.net_5._fc = nn.Identity()
        
        # Replace last layer with new layer that have num_classes nodes, after that apply Sigmoid to the output
        self.classifier = nn.Sequential(nn.Linear(kernel_count_1 + kernel_count_3 + kernel_count_4 + kernel_count_5, num_classes), nn.Sigmoid())
        
    def forward(self, x):
        
        x1 = self.net_1(x.clone())  
        x1 = x1.view(x1.size(0), -1)
        
        x3 = self.net_3(x.clone())  
        x3 = x3.view(x3.size(0), -1)
        
        x4 = self.net_4(x.clone())  
        x4 = x4.view(x4.size(0), -1)
        
        x5 = self.net_5(x.clone())  
        x5 = x5.view(x5.size(0), -1)
        
        x_all = torch.cat((x1, x3, x4, x5), dim=1)

        return self.classifier(x_all)