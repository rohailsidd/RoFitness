package com.rofitness.fitnessapp.database.dao;

import android.database.Cursor;

import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.entities.ExerciseProgress;
import com.rofitness.fitnessapp.dto.UnCompletedDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public final class ExerciseProgressDao_Impl extends ExerciseProgressDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfExerciseProgress;
    private final EntityInsertionAdapter __insertionAdapterOfExerciseProgress;
    private final SharedSQLiteStatement __preparedStmtOfResetPlanProgress;
    private final SharedSQLiteStatement __preparedStmtOfSetExerciseCompleted;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfExerciseProgress;

    public ExerciseProgressDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfExerciseProgress = new EntityInsertionAdapter<ExerciseProgress>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `ExerciseProgress`(`planId`,`weekId`,`dayId`,`exerciseId`,`completed`,`orderId`) VALUES (?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExerciseProgress exerciseProgress) {
                supportSQLiteStatement.bindLong(1, (long) exerciseProgress.getPlanId());
                supportSQLiteStatement.bindLong(2, (long) exerciseProgress.getWeekId());
                supportSQLiteStatement.bindLong(3, (long) exerciseProgress.getDayId());
                supportSQLiteStatement.bindLong(4, exerciseProgress.getExerciseId());
                supportSQLiteStatement.bindLong(5, exerciseProgress.isCompleted() ? 1 : 0);
                supportSQLiteStatement.bindLong(6, (long) exerciseProgress.getOrderId());
            }
        };
        this.__deletionAdapterOfExerciseProgress = new EntityDeletionOrUpdateAdapter<ExerciseProgress>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `ExerciseProgress` WHERE `planId` = ? AND `weekId` = ? AND `dayId` = ? AND `exerciseId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExerciseProgress exerciseProgress) {
                supportSQLiteStatement.bindLong(1, (long) exerciseProgress.getPlanId());
                supportSQLiteStatement.bindLong(2, (long) exerciseProgress.getWeekId());
                supportSQLiteStatement.bindLong(3, (long) exerciseProgress.getDayId());
                supportSQLiteStatement.bindLong(4, exerciseProgress.getExerciseId());
            }
        };
        this.__updateAdapterOfExerciseProgress = new EntityDeletionOrUpdateAdapter<ExerciseProgress>(roomDatabase) {

            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `ExerciseProgress` SET `planId` = ?,`weekId` = ?,`dayId` = ?,`exerciseId` = ?,`completed` = ?,`orderId` = ? WHERE `planId` = ? AND `weekId` = ? AND `dayId` = ? AND `exerciseId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExerciseProgress exerciseProgress) {
                supportSQLiteStatement.bindLong(1, (long) exerciseProgress.getPlanId());
                supportSQLiteStatement.bindLong(2, (long) exerciseProgress.getWeekId());
                supportSQLiteStatement.bindLong(3, (long) exerciseProgress.getDayId());
                supportSQLiteStatement.bindLong(4, exerciseProgress.getExerciseId());
                supportSQLiteStatement.bindLong(5, exerciseProgress.isCompleted() ? 1 : 0);
                supportSQLiteStatement.bindLong(6, (long) exerciseProgress.getOrderId());
                supportSQLiteStatement.bindLong(7, (long) exerciseProgress.getPlanId());
                supportSQLiteStatement.bindLong(8, (long) exerciseProgress.getWeekId());
                supportSQLiteStatement.bindLong(9, (long) exerciseProgress.getDayId());
                supportSQLiteStatement.bindLong(10, exerciseProgress.getExerciseId());
            }
        };
        this.__preparedStmtOfSetExerciseCompleted = new SharedSQLiteStatement(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE ExerciseProgress SET completed =? WHERE planId =? AND weekId =? AND dayId =? AND exerciseId =?";
            }
        };
        this.__preparedStmtOfResetPlanProgress = new SharedSQLiteStatement(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE ExerciseProgress SET completed =? WHERE planId =? ";
            }
        };
    }

    public void insert(ExerciseProgress exerciseProgress) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfExerciseProgress.insert(/*(EntityInsertionAdapter)*/ exerciseProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(ExerciseProgress... exerciseProgressArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfExerciseProgress.insert((Object[]) exerciseProgressArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<ExerciseProgress> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfExerciseProgress.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(ExerciseProgress exerciseProgress) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfExerciseProgress.handle(exerciseProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(ExerciseProgress exerciseProgress) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfExerciseProgress.handle(exerciseProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public void setExerciseCompleted(int i, int i2, int i3, long j, boolean z) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetExerciseCompleted.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, (long) (z ? 1 : 0));
            acquire.bindLong(2, (long) i);
            acquire.bindLong(3, (long) i2);
            acquire.bindLong(4, (long) i3);
            acquire.bindLong(5, j);
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetExerciseCompleted.release(acquire);
        }
    }

    @Override
    public void resetPlanProgress(int i, boolean z) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetPlanProgress.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, (long) (z ? 1 : 0));
            acquire.bindLong(2, (long) i);
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetPlanProgress.release(acquire);
        }
    }

    @Override
    public LiveData<List<ExerciseProgress>> getAllExerciesesProgressForPlan(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ExerciseProgress WHERE planId =?", 1);
        acquire.bindLong(1, (long) i);
        return new ComputableLiveData<List<ExerciseProgress>>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public List<ExerciseProgress> compute() {
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("ExerciseProgress", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    ExerciseProgressDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = ExerciseProgressDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow(AppConstants.planIdKey);
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AppConstants.weekIdKey);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow(AppConstants.dayIdKey);
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow(AppConstants.exerciseIdKey);
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("completed");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("orderId");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ExerciseProgress exerciseProgress = new ExerciseProgress();
                        exerciseProgress.setPlanId(query.getInt(columnIndexOrThrow));
                        exerciseProgress.setWeekId(query.getInt(columnIndexOrThrow2));
                        exerciseProgress.setDayId(query.getInt(columnIndexOrThrow3));
                        exerciseProgress.setExerciseId(query.getLong(columnIndexOrThrow4));
                        exerciseProgress.setCompleted(query.getInt(columnIndexOrThrow5) != 0);
                        exerciseProgress.setOrderId(query.getInt(columnIndexOrThrow6));
                        arrayList.add(exerciseProgress);
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
    public LiveData<List<ExerciseProgress>> getAllExercieses() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ExerciseProgress", 0);
        return new ComputableLiveData<List<ExerciseProgress>>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public List<ExerciseProgress> compute() {
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("ExerciseProgress", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    ExerciseProgressDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = ExerciseProgressDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow(AppConstants.planIdKey);
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AppConstants.weekIdKey);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow(AppConstants.dayIdKey);
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow(AppConstants.exerciseIdKey);
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("completed");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("orderId");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ExerciseProgress exerciseProgress = new ExerciseProgress();
                        exerciseProgress.setPlanId(query.getInt(columnIndexOrThrow));
                        exerciseProgress.setWeekId(query.getInt(columnIndexOrThrow2));
                        exerciseProgress.setDayId(query.getInt(columnIndexOrThrow3));
                        exerciseProgress.setExerciseId(query.getLong(columnIndexOrThrow4));
                        exerciseProgress.setCompleted(query.getInt(columnIndexOrThrow5) != 0);
                        exerciseProgress.setOrderId(query.getInt(columnIndexOrThrow6));
                        arrayList.add(exerciseProgress);
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
    public Integer getCompletedCount(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =? AND completed=1", 1);
        acquire.bindLong(1, (long) i);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getAllPlanCount(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =?", 1);
        acquire.bindLong(1, (long) i);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getPlanDayExercisesCount(int i, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =? AND weekId =? AND dayId =?", 3);
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        acquire.bindLong(3, (long) i3);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getCompletedCountForWeek(int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =? AND weekId =? AND completed=1", 2);
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getAllCountForWeek(int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =? AND weekId =? ", 2);
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getCompletedCountForDay(int i, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =? AND weekId =? AND dayId =?  AND completed=1", 3);
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        acquire.bindLong(3, (long) i3);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getAllCountForDay(int i, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ExerciseProgress WHERE planId =? AND weekId =?  AND dayId =? ", 3);
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        acquire.bindLong(3, (long) i3);
        Cursor query = this.__db.query(acquire);
        try {
            Integer num = null;
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                }
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public boolean isExerciseCompleted(int i, int i2, int i3, long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT completed FROM ExerciseProgress WHERE planId =? AND weekId =?  AND dayId =? AND exerciseId =? ", 4);
        long j2 = (long) i;
        boolean z = true;
        acquire.bindLong(1, j2);
        acquire.bindLong(2, (long) i2);
        acquire.bindLong(3, (long) i3);
        acquire.bindLong(4, j);
        Cursor query = this.__db.query(acquire);
        try {
            boolean z2 = false;
            if (query.moveToFirst()) {
                if (query.getInt(0) == 0) {
                    z = false;
                }
                z2 = z;
            }
            return z2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public LiveData<List<ExerciseProgress>> observeDayExercises(int i, int i2, int i3) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ExerciseProgress WHERE planId =? AND weekId =?  AND dayId =?", 3);
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        acquire.bindLong(3, (long) i3);
        return new ComputableLiveData<List<ExerciseProgress>>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public List<ExerciseProgress> compute() {
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("ExerciseProgress", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    ExerciseProgressDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = ExerciseProgressDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow(AppConstants.planIdKey);
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AppConstants.weekIdKey);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow(AppConstants.dayIdKey);
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow(AppConstants.exerciseIdKey);
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("completed");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("orderId");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ExerciseProgress exerciseProgress = new ExerciseProgress();
                        exerciseProgress.setPlanId(query.getInt(columnIndexOrThrow));
                        exerciseProgress.setWeekId(query.getInt(columnIndexOrThrow2));
                        exerciseProgress.setDayId(query.getInt(columnIndexOrThrow3));
                        exerciseProgress.setExerciseId(query.getLong(columnIndexOrThrow4));
                        exerciseProgress.setCompleted(query.getInt(columnIndexOrThrow5) != 0);
                        exerciseProgress.setOrderId(query.getInt(columnIndexOrThrow6));
                        arrayList.add(exerciseProgress);
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
    public List<UnCompletedDay> getUnCompletedDay(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT weekId,dayId from ExerciseProgress WHERE planId =?  group by weekId,dayId having count(*) != sum(completed);", 1);
        acquire.bindLong(1, (long) i);
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(AppConstants.weekIdKey);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AppConstants.dayIdKey);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                UnCompletedDay unCompletedDay = new UnCompletedDay();
                unCompletedDay.setWeekId(query.getInt(columnIndexOrThrow));
                unCompletedDay.setDayId(query.getInt(columnIndexOrThrow2));
                arrayList.add(unCompletedDay);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public UnCompletedDay getLatestUnCompletedDay(int i) {
        UnCompletedDay unCompletedDay;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT weekId, dayId from ExerciseProgress WHERE planId =?  group by weekId,dayId having count(*) != sum(completed) limit 1;", 1);
        acquire.bindLong(1, (long) i);
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(AppConstants.weekIdKey);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AppConstants.dayIdKey);
            if (query.moveToFirst()) {
                unCompletedDay = new UnCompletedDay();
                unCompletedDay.setWeekId(query.getInt(columnIndexOrThrow));
                unCompletedDay.setDayId(query.getInt(columnIndexOrThrow2));
            } else {
                unCompletedDay = null;
            }
            return unCompletedDay;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public LiveData<Integer> getLatestWeekComplete() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT weekId from ExerciseProgress group by weekId having count(*) == sum(completed) order by weekId DESC limit 1;", 0);
        return new ComputableLiveData<Integer>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public Integer compute() {
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("ExerciseProgress", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    ExerciseProgressDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = ExerciseProgressDao_Impl.this.__db.query(acquire);
                try {
                    Integer num = null;
                    if (query.moveToFirst()) {
                        if (!query.isNull(0)) {
                            num = Integer.valueOf(query.getInt(0));
                        }
                    }
                    return num;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        }.getLiveData();
    }
}
