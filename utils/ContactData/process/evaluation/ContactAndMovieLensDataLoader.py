import re
import pandas as pd
import os
import pickle
import numpy as np

class ContactAndMovieLensDataloader:
    def __init__(self, contact_dir, rating_path):
        self.contact_dir = contact_dir
        self.rating_path = rating_path

    def get_contact_file(self):
        # contact_dir = "../../contact_data/contact/"
        contact_file_list = os.listdir(self.contact_dir)
        if contact_file_list.__contains__(".DS_Store"):
            contact_file_list.remove(".DS_Store")
        return contact_file_list 

    def open_contact(self, contact_file):
        with open(self.contact_dir + contact_file, 'rb') as f:
            contact_peers = pickle.load(f)
        return contact_peers
    
    def open_rating25(self):
        # return pd.read_csv("../../contact_data/ratings25.csv")
        return pd.read_csv(self.rating_path)

    # 30% of large rating and 70% of small rating
    def split_rating(self, rating):
        count = rating["userId"].value_counts()
        threshold_ratings = count.values
        large_rating = count.index[:threshold_ratings[threshold_ratings > 400].size]
        small_rating = count.index[threshold_ratings[threshold_ratings > 400].size:]
        return large_rating, small_rating

    # insert stride
    def select_user_for_peer(self, rating, contact_peers, contact_file):
        large_rating, small_rating = self.split_rating(rating)
        peers = list(contact_peers.keys())
        peers1 = int(len(peers) * 0.3)
        peers2 = len(peers) - peers1
        selected_users1 = list(np.random.choice(large_rating, peers1, replace=False))
        selected_users2 = list(np.random.choice(small_rating, peers2, replace=False))
        selected_users = selected_users1 + selected_users2
        stride = contact_file.split('_')[2]
        return pd.DataFrame({"peerId":peers, "userId":selected_users, "stride":[stride]*len(peers), "time":[0]*len(peers), "svd_mse":[0]*len(peers), "mf_mse":[0]*len(peers), 'util':['']*len(peers)}, index=peers)

    def contact_in_hour(self, result, contact_peers):
        contact = {i:dict() for i in range(1, 30)}
        for person in contact_peers.keys():
            peer = result.loc[person].at['userId']
            for p, times in contact_peers[person].items():
                for t in times:
                    n = int(t / (60 * 60)) + 1
                    if contact[n].__contains__(peer):
                        if contact[n][peer] is None:
                            contact[n][peer] = {result.loc[p].at['userId']}
                        else:
                            contact[n][peer].add(result.loc[p].at['userId'])
                    else:
                        contact[n][peer] = {result.loc[p].at['userId']}
        return contact

    def get_contact_in_rating(self, contact_file):
        contact_peers = self.open_contact(contact_file)
        rating = self.open_rating25()
        result = self.select_user_for_peer(rating, contact_peers, contact_file)
        contact = self.contact_in_hour(result, contact_peers)
        return result, contact, rating

# if __name__ == "__main__":
#     contact_dir = "../contact_data/contact/"
#     rating_path = "../contact_data/ratings25.csv"
#     dataloader = ContactAndMovieLensDataloader(contact_dir, rating_path)
#     contact_path_list = dataloader.get_contact_file()
#     result, user_contact = dataloader.get_contact_in_rating(contact_path_list[0])
#     print(result)
#     # print(user_contact)
