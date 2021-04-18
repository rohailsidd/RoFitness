package com.rofitness.fitnessapp.database.dao;

import android.database.Cursor;

import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.rofitness.fitnessapp.database.entities.PlanWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
public final class PlanDao_Impl implements PlanDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPlanWrapper;
    private final EntityInsertionAdapter __insertionAdapterOfPlanWrapper;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfPlanWrapper;

    public PlanDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPlanWrapper = new EntityInsertionAdapter<PlanWrapper>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `PlanWrapper`(`planType`,`plan`,`id`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlanWrapper planWrapper) {
                if (planWrapper.getPlanType() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, planWrapper.getPlanType());
                }
                if (planWrapper.getPlan() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, planWrapper.getPlan());
                }
                if (planWrapper.getId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, planWrapper.getId().longValue());
                }
            }
        };
        this.__deletionAdapterOfPlanWrapper = new EntityDeletionOrUpdateAdapter<PlanWrapper>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `PlanWrapper` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlanWrapper planWrapper) {
                if (planWrapper.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, planWrapper.getId().longValue());
                }
            }
        };
        this.__updateAdapterOfPlanWrapper = new EntityDeletionOrUpdateAdapter<PlanWrapper>(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `PlanWrapper` SET `planType` = ?,`plan` = ?,`id` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlanWrapper planWrapper) {
                if (planWrapper.getPlanType() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, planWrapper.getPlanType());
                }
                if (planWrapper.getPlan() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, planWrapper.getPlan());
                }
                if (planWrapper.getId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, planWrapper.getId().longValue());
                }
                if (planWrapper.getId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, planWrapper.getId().longValue());
                }
            }
        };
    }

    public void insert(PlanWrapper planWrapper) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlanWrapper.insert(/*(EntityInsertionAdapter)*/ planWrapper);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(PlanWrapper... planWrapperArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlanWrapper.insert((Object[]) planWrapperArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<PlanWrapper> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfPlanWrapper.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(PlanWrapper planWrapper) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPlanWrapper.handle(planWrapper);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(PlanWrapper planWrapper) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPlanWrapper.handle(planWrapper);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public LiveData<List<PlanWrapper>> getAllPlans() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM PlanWrapper", 0);
        return new ComputableLiveData<List<PlanWrapper>>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public List<PlanWrapper> compute() {
                Long l;
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("PlanWrapper", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    PlanDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = PlanDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("planType");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("plan");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("id");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        PlanWrapper planWrapper = new PlanWrapper();
                        planWrapper.setPlanType(query.getString(columnIndexOrThrow));
                        planWrapper.setPlan(query.getString(columnIndexOrThrow2));
                        if (query.isNull(columnIndexOrThrow3)) {
                            l = null;
                        } else {
                            l = Long.valueOf(query.getLong(columnIndexOrThrow3));
                        }
                        planWrapper.setId(l);
                        arrayList.add(planWrapper);
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        }.getLiveData();
    }

    @Override
    public List<PlanWrapper> getAllPlan() {
        Long l;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM PlanWrapper", 0);
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("planType");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("plan");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("id");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PlanWrapper planWrapper = new PlanWrapper();
                planWrapper.setPlanType(query.getString(columnIndexOrThrow));
                planWrapper.setPlan(query.getString(columnIndexOrThrow2));
                if (query.isNull(columnIndexOrThrow3)) {
                    l = null;
                } else {
                    l = Long.valueOf(query.getLong(columnIndexOrThrow3));
                }
                planWrapper.setId(l);
                arrayList.add(planWrapper);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
