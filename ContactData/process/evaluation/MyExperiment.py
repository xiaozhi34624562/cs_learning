import pandas as pd
import numpy as np
import cornac
from cornac import Experiment
import datetime
from utils import CVExperimentResult 

class MyExperiment(Experiment):
    def __init__(
            self,
            eval_method,
            models,
            metrics,
            user_based=True,
            show_validation=True,
            verbose=False,
            save_dir=None
        ):
        super().__init__(
            eval_method=eval_method,
            models=models,
            metrics=metrics,
            user_based=True,
            show_validation=True,
            verbose=False,
            save_dir=None
        )
        self.eval_method = eval_method
        self.models = self._validate_models(models)
        self.metrics = self._validate_metrics(metrics)
        self.user_based = user_based
        self.show_validation = show_validation
        self.verbose = verbose
        self.save_dir = save_dir
        self.result = None
        self.val_result = None
    
    def run(self):
        """Run the Cornac experiment"""
        self._create_result()

        for model in self.models:
            test_result, val_result = self.eval_method.evaluate(
                model=model,
                metrics=self.metrics,
                user_based=self.user_based,
                show_validation=self.show_validation,
            )

            self.result.append(test_result)
            if self.val_result is not None:
                self.val_result.append(val_result)

            if not isinstance(self.result, CVExperimentResult):
                model.save(self.save_dir)

        # output = ""
        # if self.val_result is not None:
        #     output += "\nVALIDATION:\n...\n{}".format(self.val_result)
        # output += "\nTEST:\n...\n{}".format(self.result)
        # return float(self.result.__str__().split("|")[-3].strip())
        return self.result.__str__().split("|")[-3].strip()
