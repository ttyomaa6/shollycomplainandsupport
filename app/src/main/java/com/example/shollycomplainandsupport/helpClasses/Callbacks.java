package com.example.shollycomplainandsupport.helpClasses;

import com.example.shollycomplainandsupport.helpClasses.Clothes;
import com.example.shollycomplainandsupport.helpClasses.Subscriber;
import com.example.shollycomplainandsupport.helpClasses.UserPeopleAdapter;

import org.w3c.dom.Comment;

import java.security.cert.CertPathValidatorException;
import java.util.ArrayList;

public class Callbacks {
    public interface UniqueNick{
        void uniqueNicknameCallback(boolean isUnique);
    }
    public interface hasGoogleUser{
        void hasGoogleUserCallback(boolean hasThisUser);
    }
    public interface passUserDataBetweenFragments{
        void passUserData(UserInformation info);
    }
    public interface LoadUserDataInterface{
        void LoadData(ArrayList<UserInformation> data);
    }
    public interface PassLoadUserDataInterface{
        void PassData(ArrayList<UserInformation> data);
    }
    public interface GetUserNickByUid{
        void PassUserNick(String nick);
    }
    public interface GetMinerFromBase{
        void GetMinerFromBase(ArrayList<Miner> minersFromBase);
    }

    public interface buyMiner{
        void buyMiner(Miner miner);
    }
    public interface HasUid{
        void HasUidCallback(boolean HasUid);
    }
    public interface GetMyMinerFromBase{
        void GetMyMinerFromBase(ArrayList<Miner> myMinersFromBase);
    }
    public interface GetActiveMiners{
        void GetActiveMiners(ArrayList<Miner> activeMinersFromBase);
    }
    public interface MoneyFromBase{
        void GetMoneyFromBase(long money);
    }
    public interface GetTodayMining{
        void GetTodayMining(double todayMiningFromBase);
    }
    public interface GetTimesTamp{
        void GetTimesTamp(long timesTamp);
    }

    public interface GetBio{
        void GetBiography(String bio);
    }
    public interface GetClothes{
        default void getClothes(ArrayList<Clothes> allClothes) {
        }
    }
    public interface GetAlreadySearched{
        default void getAlreadySearched(ArrayList<UserPeopleAdapter> searchedUserFromBase) {
        }
    }
    public interface getFriendsList{
        default void getFriendsList(ArrayList<Subscriber> friends){}
    }
    public interface getAmountOfFriends{
        default void getAmountOfFriends(int amount){}
    }
    public interface getSubscribersList{
        default void getSubscribersList(ArrayList<Subscriber> subscribers){}
    }
    public interface getSavedLook{
        default void getSavedLook(ArrayList<NewsItem> newsItems){}
    }
    public interface getCommentsList{
        default void getCommentsList(ArrayList<Comment> comment){}
    }
    public interface getLookClothes{
        default void getLookClothes(ArrayList<Clothes> clothesArrayList){}
    }
    public interface getNontificationsList{
        default void getNontificationsList(ArrayList<Nontification> nontifications){}
    }
    public interface getComplainReasonsList{
        default void getComplainReasonsList(ArrayList<Reason> reason){}
    }
    public interface getLooksList{
        default void getLooksList(ArrayList<NewsItem> look){}
    }
    public interface getTransferHistory{
        default void getTransferHistory(ArrayList<Transfer> transfers){}
    }
    public interface getAmountOfSubscribers{
        default void getAmountOfSubscribers(int amount){}
    }
    public interface getSubsCount{
        void getCount(Long subsCount);
    }
    public interface getRecommendationsThread{
        void getRecommendationsInterface(ArrayList<UserPeopleAdapter> recommendationsArrayList);
    }
}