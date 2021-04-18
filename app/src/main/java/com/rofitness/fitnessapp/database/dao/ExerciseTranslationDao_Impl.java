package com.rofitness.fitnessapp.database.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.rofitness.fitnessapp.database.rohailwork.entities.ExerciseTranslation;

import java.util.List;

@SuppressWarnings("all")
public final class ExerciseTranslationDao_Impl implements ExerciseTranslationDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfExerciseTranslation;
    private final EntityInsertionAdapter __insertionAdapterOfExerciseTranslation;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfExerciseTranslation;

    public ExerciseTranslationDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfExerciseTranslation = new EntityInsertionAdapter<ExerciseTranslation>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `ExerciseTranslation`(`languageId`,`exerciseId`,`exerciseTitle`,`detail`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExerciseTranslation exerciseTranslation) {
                if (exerciseTranslation.getLanguageId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, exerciseTranslation.getLanguageId().longValue());
                }
                if (exerciseTranslation.getExerciseId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, exerciseTranslation.getExerciseId().longValue());
                }
                if (exerciseTranslation.getExerciseTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, exerciseTranslation.getExerciseTitle());
                }
                if (exerciseTranslation.getDetail() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, exerciseTranslation.getDetail());
                }
            }
        };
        this.__deletionAdapterOfExerciseTranslation = new EntityDeletionOrUpdateAdapter<ExerciseTranslation>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `ExerciseTranslation` WHERE `languageId` = ? AND `exerciseId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExerciseTranslation exerciseTranslation) {
                if (exerciseTranslation.getLanguageId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, exerciseTranslation.getLanguageId().longValue());
                }
                if (exerciseTranslation.getExerciseId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, exerciseTranslation.getExerciseId().longValue());
                }
            }
        };
        this.__updateAdapterOfExerciseTranslation = new EntityDeletionOrUpdateAdapter<ExerciseTranslation>(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `ExerciseTranslation` SET `languageId` = ?,`exerciseId` = ?,`exerciseTitle` = ?,`detail` = ? WHERE `languageId` = ? AND `exerciseId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExerciseTranslation exerciseTranslation) {
                if (exerciseTranslation.getLanguageId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, exerciseTranslation.getLanguageId().longValue());
                }
                if (exerciseTranslation.getExerciseId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, exerciseTranslation.getExerciseId().longValue());
                }
                if (exerciseTranslation.getExerciseTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, exerciseTranslation.getExerciseTitle());
                }
                if (exerciseTranslation.getDetail() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, exerciseTranslation.getDetail());
                }
                if (exerciseTranslation.getLanguageId() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, exerciseTranslation.getLanguageId().longValue());
                }
                if (exerciseTranslation.getExerciseId() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, exerciseTranslation.getExerciseId().longValue());
                }
            }
        };
    }

    public void insert(ExerciseTranslation exerciseTranslation) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfExerciseTranslation.insert(/*(EntityInsertionAdapter)*/ exerciseTranslation);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(ExerciseTranslation... exerciseTranslationArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfExerciseTranslation.insert((Object[]) exerciseTranslationArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<ExerciseTranslation> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfExerciseTranslation.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(ExerciseTranslation exerciseTranslation) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfExerciseTranslation.handle(exerciseTranslation);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(ExerciseTranslation exerciseTranslation) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfExerciseTranslation.handle(exerciseTranslation);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
