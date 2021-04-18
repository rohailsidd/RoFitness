package com.rofitness.fitnessapp.database.dao;

import androidx.lifecycle.LiveData;

import com.rofitness.fitnessapp.database.entities.UserAccount;

public interface UserAccountDao extends BaseDao<UserAccount> {
    LiveData<UserAccount> getUserAccount();
}
