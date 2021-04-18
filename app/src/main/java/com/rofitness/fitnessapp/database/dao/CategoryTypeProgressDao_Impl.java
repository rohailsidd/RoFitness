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
import com.rofitness.fitnessapp.database.entities.CategoryTypeProgress;
import com.rofitness.fitnessapp.database.entities.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public final class CategoryTypeProgressDao_Impl extends CategoryTypeProgressDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCategoryTypeProgress;
    private final EntityInsertionAdapter __insertionAdapterOfCategoryTypeProgress;
    private final SharedSQLiteStatement __preparedStmtOfResetCategoryTypeProgress;
    private final SharedSQLiteStatement __preparedStmtOfSetCompleted;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfCategoryTypeProgress;

    public CategoryTypeProgressDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfCategoryTypeProgress = new EntityInsertionAdapter<CategoryTypeProgress>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `CategoryTypeProgress`(`mainMuscleGroup`,`exerciseId`,`isCompleted`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CategoryTypeProgress categoryTypeProgress) {
                if (categoryTypeProgress.getMainMuscleGroup() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, categoryTypeProgress.getMainMuscleGroup());
                }
                supportSQLiteStatement.bindLong(2, categoryTypeProgress.getExerciseId());
                supportSQLiteStatement.bindLong(3, categoryTypeProgress.isCompleted() ? 1 : 0);
            }
        };
        this.__deletionAdapterOfCategoryTypeProgress = new EntityDeletionOrUpdateAdapter<CategoryTypeProgress>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `CategoryTypeProgress` WHERE `mainMuscleGroup` = ? AND `exerciseId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CategoryTypeProgress categoryTypeProgress) {
                if (categoryTypeProgress.getMainMuscleGroup() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, categoryTypeProgress.getMainMuscleGroup());
                }
                supportSQLiteStatement.bindLong(2, categoryTypeProgress.getExerciseId());
            }
        };
        this.__updateAdapterOfCategoryTypeProgress = new EntityDeletionOrUpdateAdapter<CategoryTypeProgress>(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `CategoryTypeProgress` SET `mainMuscleGroup` = ?,`exerciseId` = ?,`isCompleted` = ? WHERE `mainMuscleGroup` = ? AND `exerciseId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CategoryTypeProgress categoryTypeProgress) {
                if (categoryTypeProgress.getMainMuscleGroup() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, categoryTypeProgress.getMainMuscleGroup());
                }
                supportSQLiteStatement.bindLong(2, categoryTypeProgress.getExerciseId());
                supportSQLiteStatement.bindLong(3, categoryTypeProgress.isCompleted() ? 1 : 0);
                if (categoryTypeProgress.getMainMuscleGroup() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, categoryTypeProgress.getMainMuscleGroup());
                }
                supportSQLiteStatement.bindLong(5, categoryTypeProgress.getExerciseId());
            }
        };
        this.__preparedStmtOfResetCategoryTypeProgress = new SharedSQLiteStatement(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE CATEGORYTYPEPROGRESS SET isCompleted =? WHERE mainMuscleGroup =? ";
            }
        };
        this.__preparedStmtOfSetCompleted = new SharedSQLiteStatement(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE CATEGORYTYPEPROGRESS SET isCompleted =? WHERE exerciseId =? AND mainMuscleGroup =?";
            }
        };
    }

    public void insert(CategoryTypeProgress categoryTypeProgress) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCategoryTypeProgress.insert(/*(EntityInsertionAdapter)*/ categoryTypeProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(CategoryTypeProgress... categoryTypeProgressArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCategoryTypeProgress.insert((Object[]) categoryTypeProgressArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<CategoryTypeProgress> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfCategoryTypeProgress.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(CategoryTypeProgress categoryTypeProgress) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCategoryTypeProgress.handle(categoryTypeProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(CategoryTypeProgress categoryTypeProgress) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfCategoryTypeProgress.handle(categoryTypeProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public void resetCategoryTypeProgress(String str, boolean z) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetCategoryTypeProgress.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, (long) (z ? 1 : 0));
            if (str == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str);
            }
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetCategoryTypeProgress.release(acquire);
        }
    }

    @Override
    public void setCompleted(long j, String str, boolean z) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetCompleted.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, (long) (z ? 1 : 0));
            acquire.bindLong(2, j);
            if (str == null) {
                acquire.bindNull(3);
            } else {
                acquire.bindString(3, str);
            }
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetCompleted.release(acquire);
        }
    }

    @Override
    public LiveData<List<CategoryTypeProgress>> getAllCategoryTypeProgressForMainMuscleGroup(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM CATEGORYTYPEPROGRESS WHERE mainMuscleGroup = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new ComputableLiveData<List<CategoryTypeProgress>>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public List<CategoryTypeProgress> compute() {
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("CATEGORYTYPEPROGRESS", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    CategoryTypeProgressDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = CategoryTypeProgressDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("mainMuscleGroup");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AppConstants.exerciseIdKey);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("isCompleted");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new CategoryTypeProgress(query.getString(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3) != 0));
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
    public Integer getMainMuscleGroupExercisesCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM CategoryTypeProgress WHERE mainMuscleGroup =?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
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
    public Integer getCompletedCountForMainMuscleGroup(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM CATEGORYTYPEPROGRESS WHERE mainMuscleGroup =? AND isCompleted=1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
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
    public Integer getAllCountForMainMuscleGroup(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM CATEGORYTYPEPROGRESS WHERE mainMuscleGroup =?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
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
    public boolean getExerciseFlag(long j, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT isCompleted FROM CATEGORYTYPEPROGRESS WHERE exerciseId =? AND mainMuscleGroup =?", 2);
        boolean z = true;
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
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
    public List<Exercise> getExercisesByTag(String str, Long l) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        Long l2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM NewExercise e join CategoryTypeProgress ctp on e.id = ctp.exerciseId JOIN exercisetranslation et ON e.id= et.exerciseId and et.languageId = ? where ctp.mainMuscleGroup = ?", 2);
        if (l == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, l.longValue());
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("detailLink");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("mainMuscleGroup");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("otherMuscleGroups");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("mainBodyGroup");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("tags");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("type");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("equipment");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("mechanics");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("difficulty");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("image1Link");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("image2Link");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("sets");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("repetitions");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoLink");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("mainMuscleGroup");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("exerciseTitle");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("detail");
                int i = columnIndexOrThrow15;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Exercise exercise = new Exercise();
                    exercise.setDetailLink(query.getString(columnIndexOrThrow));
                    exercise.setMainMuscleGroup(query.getString(columnIndexOrThrow2));
                    exercise.setOtherMuscleGroups(query.getString(columnIndexOrThrow3));
                    exercise.setMainBodyGroup(query.getString(columnIndexOrThrow4));
                    exercise.setTags(query.getString(columnIndexOrThrow5));
                    exercise.setType(query.getString(columnIndexOrThrow6));
                    exercise.setEquipment(query.getString(columnIndexOrThrow7));
                    exercise.setMechanics(query.getString(columnIndexOrThrow8));
                    exercise.setDifficulty(query.getString(columnIndexOrThrow9));
                    exercise.setImage1Link(query.getString(columnIndexOrThrow10));
                    exercise.setImage2Link(query.getString(columnIndexOrThrow11));
                    if (query.isNull(columnIndexOrThrow12)) {
                        l2 = null;
                    } else {
                        l2 = Long.valueOf(query.getLong(columnIndexOrThrow12));
                    }
                    exercise.setId(l2);
                    exercise.setSets(query.getString(columnIndexOrThrow13));
                    exercise.setRepetitions(query.getString(columnIndexOrThrow14));
                    exercise.setVideoLink(query.getString(i));
                    exercise.setMainMuscleGroup(query.getString(columnIndexOrThrow16));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    exercise.setExerciseTitle(query.getString(columnIndexOrThrow17));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    exercise.setDetail(query.getString(columnIndexOrThrow18));
                    arrayList.add(exercise);
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    arrayList = arrayList;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow14 = columnIndexOrThrow14;
                    i = i;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
        }
        return null;
    }
}
