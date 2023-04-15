import os
import cornac
import datetime
from similarity import get_similarity_dict
from MyExperiment import MyExperiment
import pandas as pd
from ContactAndMovieLensDataLoader import ContactAndMovieLensDataloader


class Eval(ContactAndMovieLensDataloader):
    def __init__(self, contact_dir, rating_path):
        super().__init__(contact_dir=contact_dir, rating_path=rating_path)
        self.contact_dir = contact_dir
        self.rating_path = rating_path        

    def transformDataToCornacForm(self, rating, peers):
        data = (rating.loc[rating["userId"].isin(peers)]).drop(labels="timestamp", axis=1)
        data = [(str(data.iloc[i][0]), str(data.iloc[i][1]), float(data.iloc[i][2])) for i in range(data.shape[0])]
        return data

    def getEachUserFromContact(self, contact):
        user_list = list()
        for k, v in contact.items():
            item = list()
            item.append(k)
            item.extend(v)
            user_list.append(item)
        return user_list

    # user contact in hour
    def getUserListsFromContact(self, contact):
        user_lists = list()
        for t in contact.keys():
            user_list = list()
            for k, v in contact[t].items():
                item = list()
                item.append(k)
                item.extend(v)
                user_list.append(item)
            user_lists.append(user_list)
        return user_lists

    def single_user_cornac(self, peers, rating, mf):
        """
        if mf is true, model is mf. otherwise model is svd
        """
        data = self.transformDataToCornacForm(rating, peers)
        # ratio_split = cornac.eval_methods.stratified_split.StratifiedSplit(data, group_by='user', fmt='UIR', test_size=0.2)
        ratio_split = cornac.eval_methods.RatioSplit(data=data, test_size=0.2, exclude_unknowns=False)

        if mf:
            model = cornac.models.MF(
                k=10,
                max_iter=10,
                learning_rate=0.01,
                lambda_reg=0.02,
                use_bias=True,
                early_stop=True,
                verbose=True,
            )
        else:
            model = cornac.models.SVD(
                k=10,
                max_iter=10,
                learning_rate=0.01,
                lambda_reg=0.02,
                verbose=True
            )

        mse = cornac.metrics.MSE()

        re = MyExperiment(
            eval_method=ratio_split,
            models=[model],
            metrics=[mse]
        ).run()
        return re

    def cornac_all_users(self, user_list, rating, mf, result):
        mses = list()
        idx = 0
        for user in user_list:
            mse = self.single_user_cornac(user, rating, mf)
            if mf:
                if result.loc[result['userId'] == user, 'mf_mse'].values[0] != 0:
                    result.loc[result['userId'] == user, 'mf_mse'] = mse
                    result.loc[result['userId'] == user, 'time'] = idx
                else:
                    result.loc[result['userId'] == user, 'util'] += "_mf_" + str(idx) + "_" + str(mse)
            else:
                if result.loc[result['userId'] == user, 'svd_mse'].values[0] != 0:
                    result.loc[result['userId'] == user, 'svd_mse'] = mse
                    result.loc[result['userId'] == user, 'time'] = idx
                else:
                    result.loc[result['userId'] == user, 'util'] += "_svd" + str(idx) + "_" + str(mse)
        return result

    def cornac_all_user_lists(self, user_lists, rating, mf, result):
        idx = 0
        for users in user_lists:
            idx += 1
            for user in users:
                mse = self.single_user_cornac(user, rating, mf)
                if mf:
                    if result.loc[result['userId'] == user[0], 'mf_mse'].values[0] == 0:
                        result.loc[result['userId'] == user[0], 'mf_mse'] = mse
                        result.loc[result['userId'] == user[0], 'time'] = idx
                    else:
                        result.loc[result['userId'] == user[0], 'util'] += "_mf_" + str(idx) + "_" + str(mse)
                else:
                    if result.loc[result['userId'] == user[0], 'svd_mse'].values[0] == 0:
                        result.loc[result['userId'] == user[0], 'svd_mse'] = mse
                        result.loc[result['userId'] == user[0], 'time'] = idx
                    else:
                        result.loc[result['userId'] == user[0], 'util'] += "_svd" + str(idx) + "_" + str(mse)
        return result

    # def get_path_of_contact_and_rating(self):
    #     contact_dir = "../../contact_data/userMovie/"
    #     rating_dir = "../../contact_data/"
    # 
    #     contact_list = os.listdir(contact_dir)
    #     rating_list = os.listdir(rating_dir)
    # 
    #     if contact_list.__contains__(".DS_Store"):
    #         contact_list.remove(".DS_Store")
    # 
    #     if rating_list.__contains__(".DS_Store"):
    #         rating_list.remove(".DS_Store")
    # 
    #     rating_and_contact_path = list()
    #     for contact_file in contact_list:
    #         idx = contact_file.split(".")[0].split("_")[-1]
    #         for user in rating_list:
    #             if user.__contains__(idx):
    #                 rating_and_contact_path.append([rating_dir + user, contact_dir + contact_file])
    #                 break
    # 
    #     return rating_and_contact_path

    def save(self, result, contact_file):
        name = "./contact_mse_info/contact_mse_info_" + contact_file.split("_")[2] + ".csv"
        result.to_csv(name)

    # def main(self):
    #     rating_and_contact_path = self.get_path_of_contact_and_rating()
    #     for path in rating_and_contact_path:
    #         sim_dict, contact, rating = get_similarity_dict(path[0], path[1])
    #         user_list = self.getEachUserFromContact(contact)
    #         mses_mf = self.cornac_all_users(user_list, rating, True)
    #         mses_svd = self.cornac_all_users(user_list, rating, False)
    #         avg_mf_mse = round(sum(mses_mf) / len(mses_mf), 5)
    #         avg_svd_mse = round(sum(mses_svd) / len(mses_svd), 5)
    #         self.save(path[0] + "       " + str(avg_mf_mse) + "         " + str(avg_svd_mse))

    def result_with_time(self, contact_file):
        result, contact, rating = self.get_contact_in_rating(contact_file)
        # sim_dict, contact, rating = get_similarity_dict(path[0], path[1])
        user_lists = self.getUserListsFromContact(contact)
        result = self.cornac_all_user_lists(user_lists, rating, True, result)
        result = self.cornac_all_user_lists(user_lists, rating, False, result)
        self.save(result, contact_file)

    def main(self):
        contact_file_list = self.get_contact_file()
        for contact_file in contact_file_list:
            print("begin to train" + contact_file)
            self.result_with_time(contact_file)


if __name__ == "__main__":
    contact_dir = "../../contact_data/contact/"
    rating_path = "../../contact_data/ratings25.csv"
    print("I have get the dir")
    eval = Eval(contact_dir, rating_path)
    eval.main()

# sim_dict, contact, rating = get_similarity_dict("../../contact_data/rating_7474.csv", "../../contact_data/userMovie/contact_7474.pkl")
# user_list = getEachUserFromContact(contact)
# mses = cornac_mf_all(user_list, rating)
# print(sum(mses)/len(mses))

# user_list2 = list()
# for user in user_list:
#     if len(user) > 3:
#         user_list2.append(user)
#
# mses = cornac_mf_all(user_list2, rating)
