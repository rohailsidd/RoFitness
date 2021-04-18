package com.rofitness.fitnessapp.database.dao;

import android.database.Cursor;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.rofitness.fitnessapp.database.dataobjects.Category;
import com.rofitness.fitnessapp.database.dataobjects.SubCategory;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.database.rohailwork.entities.NewExercise;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public final class ExerciseDao_Impl implements ExerciseDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNewExercise;
    private final EntityInsertionAdapter __insertionAdapterOfNewExercise;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfNewExercise;

    public ExerciseDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfNewExercise = new EntityInsertionAdapter<NewExercise>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `NewExercise`(`detailLink`,`mainMuscleGroup`,`otherMuscleGroups`,`mainBodyGroup`,`tags`,`type`,`equipment`,`mechanics`,`difficulty`,`image1Link`,`image2Link`,`id`,`sets`,`repetitions`,`videoLink`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, NewExercise newExercise) {
                if (newExercise.getDetailLink() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, newExercise.getDetailLink());
                }
                if (newExercise.getMainMuscleGroup() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, newExercise.getMainMuscleGroup());
                }
                if (newExercise.getOtherMuscleGroups() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, newExercise.getOtherMuscleGroups());
                }
                if (newExercise.getMainBodyGroup() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, newExercise.getMainBodyGroup());
                }
                if (newExercise.getTags() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, newExercise.getTags());
                }
                if (newExercise.getType() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, newExercise.getType());
                }
                if (newExercise.getEquipment() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, newExercise.getEquipment());
                }
                if (newExercise.getMechanics() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, newExercise.getMechanics());
                }
                if (newExercise.getDifficulty() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, newExercise.getDifficulty());
                }
                if (newExercise.getImage1Link() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, newExercise.getImage1Link());
                }
                if (newExercise.getImage2Link() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, newExercise.getImage2Link());
                }
                if (newExercise.getId() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindLong(12, newExercise.getId().longValue());
                }
                if (newExercise.getSets() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, newExercise.getSets());
                }
                if (newExercise.getRepetitions() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, newExercise.getRepetitions());
                }
                if (newExercise.getVideoLink() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, newExercise.getVideoLink());
                }
            }
        };
        this.__deletionAdapterOfNewExercise = new EntityDeletionOrUpdateAdapter<NewExercise>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `NewExercise` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, NewExercise newExercise) {
                if (newExercise.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, newExercise.getId().longValue());
                }
            }
        };
        this.__updateAdapterOfNewExercise = new EntityDeletionOrUpdateAdapter<NewExercise>(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `NewExercise` SET `detailLink` = ?,`mainMuscleGroup` = ?,`otherMuscleGroups` = ?,`mainBodyGroup` = ?,`tags` = ?,`type` = ?,`equipment` = ?,`mechanics` = ?,`difficulty` = ?,`image1Link` = ?,`image2Link` = ?,`id` = ?,`sets` = ?,`repetitions` = ?,`videoLink` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, NewExercise newExercise) {
                if (newExercise.getDetailLink() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, newExercise.getDetailLink());
                }
                if (newExercise.getMainMuscleGroup() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, newExercise.getMainMuscleGroup());
                }
                if (newExercise.getOtherMuscleGroups() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, newExercise.getOtherMuscleGroups());
                }
                if (newExercise.getMainBodyGroup() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, newExercise.getMainBodyGroup());
                }
                if (newExercise.getTags() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, newExercise.getTags());
                }
                if (newExercise.getType() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, newExercise.getType());
                }
                if (newExercise.getEquipment() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, newExercise.getEquipment());
                }
                if (newExercise.getMechanics() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, newExercise.getMechanics());
                }
                if (newExercise.getDifficulty() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, newExercise.getDifficulty());
                }
                if (newExercise.getImage1Link() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, newExercise.getImage1Link());
                }
                if (newExercise.getImage2Link() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, newExercise.getImage2Link());
                }
                if (newExercise.getId() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindLong(12, newExercise.getId().longValue());
                }
                if (newExercise.getSets() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, newExercise.getSets());
                }
                if (newExercise.getRepetitions() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, newExercise.getRepetitions());
                }
                if (newExercise.getVideoLink() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, newExercise.getVideoLink());
                }
                if (newExercise.getId() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindLong(16, newExercise.getId().longValue());
                }
            }
        };
    }

    public void insert(NewExercise newExercise) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfNewExercise.insert(/*(EntityInsertionAdapter)*/ newExercise);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(NewExercise... newExerciseArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfNewExercise.insert((Object[]) newExerciseArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<NewExercise> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfNewExercise.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(NewExercise newExercise) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfNewExercise.handle(newExercise);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(NewExercise newExercise) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfNewExercise.handle(newExercise);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<SubCategory> getSubCategories(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT mainMuscleGroup from NewExercise WHERE mainBodyGroup = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mainMuscleGroup");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new SubCategory(query.getString(columnIndexOrThrow)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public List<Category> getCategories() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT mainBodyGroup from NewExercise group by mainBodyGroup", 0);
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mainBodyGroup");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new Category(query.getString(columnIndexOrThrow)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Integer getExerciseCountInsideCategory(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(distinct mainMuscleGroup) from NewExercise where mainBodyGroup like ?", 1);
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
    public Integer getExerciseCountInsideSubCategory(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) from CategoryTypeProgress where mainMuscleGroup LIKE ?", 1);
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
    public List<Exercise> getExercisesByCategoryName(String str, Long l) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        Long l2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from NewExercise INNER JOIN exerciseTranslation ON newexercise.id= exerciseTranslation.exerciseId where exerciseTranslation.languageId=? and NewExercise.mainMuscleGroup LIKE ?", 2);
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
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("repetitions");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoLink");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("exerciseTitle");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("detail");
                int i = columnIndexOrThrow14;
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
                    exercise.setRepetitions(query.getString(i));
                    exercise.setVideoLink(query.getString(columnIndexOrThrow15));
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    exercise.setExerciseTitle(query.getString(columnIndexOrThrow16));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    exercise.setDetail(query.getString(columnIndexOrThrow17));
                    arrayList = arrayList;
                    arrayList.add(exercise);
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    columnIndexOrThrow = columnIndexOrThrow;
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

    @Override
    public Exercise getSingleExercise(long j, Long l) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from NewExercise INNER JOIN exerciseTranslation ON newexercise.id= exerciseTranslation.exerciseId where exerciseTranslation.languageId=? and  NewExercise.id =?", 2);
        if (l == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, l.longValue());
        }
        acquire.bindLong(2, j);
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
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("repetitions");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoLink");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("exerciseTitle");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("detail");
                Exercise exercise = null;
                Long valueOf = null;
                if (query.moveToFirst()) {
                    Exercise exercise2 = new Exercise();
                    exercise2.setDetailLink(query.getString(columnIndexOrThrow));
                    exercise2.setMainMuscleGroup(query.getString(columnIndexOrThrow2));
                    exercise2.setOtherMuscleGroups(query.getString(columnIndexOrThrow3));
                    exercise2.setMainBodyGroup(query.getString(columnIndexOrThrow4));
                    exercise2.setTags(query.getString(columnIndexOrThrow5));
                    exercise2.setType(query.getString(columnIndexOrThrow6));
                    exercise2.setEquipment(query.getString(columnIndexOrThrow7));
                    exercise2.setMechanics(query.getString(columnIndexOrThrow8));
                    exercise2.setDifficulty(query.getString(columnIndexOrThrow9));
                    exercise2.setImage1Link(query.getString(columnIndexOrThrow10));
                    exercise2.setImage2Link(query.getString(columnIndexOrThrow11));
                    if (!query.isNull(columnIndexOrThrow12)) {
                        valueOf = Long.valueOf(query.getLong(columnIndexOrThrow12));
                    }
                    exercise2.setId(valueOf);
                    exercise2.setSets(query.getString(columnIndexOrThrow13));
                    exercise2.setRepetitions(query.getString(columnIndexOrThrow14));
                    exercise2.setVideoLink(query.getString(columnIndexOrThrow15));
                    exercise2.setExerciseTitle(query.getString(columnIndexOrThrow16));
                    exercise2.setDetail(query.getString(columnIndexOrThrow17));
                    exercise = exercise2;
                }
                query.close();
                roomSQLiteQuery.release();
                return exercise;
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

    @Override
    public List<Exercise> findByIds(List<Long> list, Long l) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        Long l2;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT * FROM NewExercise INNER JOIN exerciseTranslation ON newexercise.id= exerciseTranslation.exerciseId where exerciseTranslation.languageId=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and  NewExercise.id IN(");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
        if (l == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, l.longValue());
        }
        int i = 2;
        for (Long l3 : list) {
            if (l3 == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l3.longValue());
            }
            i++;
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
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("repetitions");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoLink");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("exerciseTitle");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("detail");
                int i2 = columnIndexOrThrow14;
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
                    exercise.setRepetitions(query.getString(i2));
                    exercise.setVideoLink(query.getString(columnIndexOrThrow15));
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    exercise.setExerciseTitle(query.getString(columnIndexOrThrow16));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    exercise.setDetail(query.getString(columnIndexOrThrow17));
                    arrayList = arrayList;
                    arrayList.add(exercise);
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    columnIndexOrThrow = columnIndexOrThrow;
                    i2 = i2;
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

    @Override
    public List<Exercise> findByIds(long j, int i, int i2, Long l) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        Long l2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM NewExercise INNER JOIN exerciseTranslation ON newexercise.id= exerciseTranslation.exerciseId JOIN ExerciseProgress ON  ExerciseProgress.exerciseId =   newexercise.id where exerciseTranslation.languageId=? and ExerciseProgress.planId =?  AND ExerciseProgress.weekId =? AND ExerciseProgress.dayId =? ORDER BY orderId", 4);
        if (l == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, l.longValue());
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, (long) i);
        acquire.bindLong(4, (long) i2);
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
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("repetitions");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoLink");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("exerciseTitle");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("detail");
                int i3 = columnIndexOrThrow14;
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
                    exercise.setRepetitions(query.getString(i3));
                    exercise.setVideoLink(query.getString(columnIndexOrThrow15));
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    exercise.setExerciseTitle(query.getString(columnIndexOrThrow16));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    exercise.setDetail(query.getString(columnIndexOrThrow17));
                    arrayList = arrayList;
                    arrayList.add(exercise);
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    columnIndexOrThrow = columnIndexOrThrow;
                    i3 = i3;
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
