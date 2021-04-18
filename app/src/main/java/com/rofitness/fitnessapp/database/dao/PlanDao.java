package com.rofitness.fitnessapp.database.dao;

import androidx.lifecycle.LiveData;

import com.rofitness.fitnessapp.database.entities.PlanWrapper;

import java.util.List;

public interface PlanDao extends BaseDao<PlanWrapper> {
    List<PlanWrapper> getAllPlan();

    LiveData<List<PlanWrapper>> getAllPlans();
}
