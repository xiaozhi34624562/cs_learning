import pandas as pd
import pickle

def get_similarity_dict(rating_path, contact_path):
    info = pd.read_csv(rating_path)
    with open(contact_path, 'rb') as f:
        contact = pickle.load(f)
    sim_dict = dict()

    # users = info.loc[:, "userId"].astype(str).drop_duplicates().values
    # movies = info.loc[:, "movieId"].astype(str).drop_duplicates().values
    # user_dict = {user_id : idx for idx, user_id in enumerate(users)}
    # movie_dict = {movie_id : idx for idx, movie_id in enumerate(movies)}
    #
    # row = info.loc[:, "userId"].astype(str).values
    # col = info.loc[:, "movieId"].astype(str).values
    # row = [user_dict[id] for id in row]
    # col = [movie_dict[id] for id in col]
    # data = info.loc[:, "rating"].astype(np.float64).values
    # D_coo_matrix = coo_matrix((data, (row, col)))
    # D_sparse = csr_matrix(D_coo_matrix)
    # similarity_matrix = cosine_similarity(D_sparse, dense_output=True)
    #
    # sim_dict = dict()
    # for idx in contact.keys():
    #     for k, v in contact[idx].items():
    #         for p in contact[idx][k]:
    #             user_sim_key_1 = str(k) + "-" + str(p)
    #             user_sim_key_2 = str(p) + "_" + str(k)
    #             if sim_dict.__contains__(user_sim_key_1) or sim_dict.__contains__(user_sim_key_2):
    #                 continue
    #             else:
    #                 sim_dict[user_sim_key_1] = similarity_matrix[user_dict[str(k)], user_dict[str(p)]]
    return sim_dict, contact, info