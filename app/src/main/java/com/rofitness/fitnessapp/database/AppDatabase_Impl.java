package com.rofitness.fitnessapp.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.dao.CategoryTypeProgressDao;
import com.rofitness.fitnessapp.database.dao.CategoryTypeProgressDao_Impl;
import com.rofitness.fitnessapp.database.dao.ExerciseDao;
import com.rofitness.fitnessapp.database.dao.ExerciseDao_Impl;
import com.rofitness.fitnessapp.database.dao.ExerciseProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseProgressDao_Impl;
import com.rofitness.fitnessapp.database.dao.ExerciseTranslationDao;
import com.rofitness.fitnessapp.database.dao.ExerciseTranslationDao_Impl;
import com.rofitness.fitnessapp.database.dao.LanguageDao;
import com.rofitness.fitnessapp.database.dao.LanguageDao_Impl;
import com.rofitness.fitnessapp.database.dao.PlanDao;
import com.rofitness.fitnessapp.database.dao.PlanDao_Impl;
import com.rofitness.fitnessapp.database.dao.UserAccountDao;
import com.rofitness.fitnessapp.database.dao.UserAccountDao_Impl;

import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("ALL")
public final class AppDatabase_Impl extends AppDatabase {
    private volatile CategoryTypeProgressDao _categoryTypeProgressDao;
    private volatile ExerciseDao _exerciseDao;
    private volatile ExerciseProgressDao _exerciseProgressDao;
    private volatile ExerciseTranslationDao _exerciseTranslationDao;
    private volatile LanguageDao _languageDao;
    private volatile PlanDao _planDao;
    private volatile UserAccountDao _userAccountDao;

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) {
            @Override
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `ExerciseProgress` (`planId` INTEGER NOT NULL, `weekId` INTEGER NOT NULL, `dayId` INTEGER NOT NULL, `exerciseId` INTEGER NOT NULL, `completed` INTEGER NOT NULL, `orderId` INTEGER NOT NULL, PRIMARY KEY(`planId`, `weekId`, `dayId`, `exerciseId`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `UserAccount` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `image` TEXT, `heightUnit` TEXT, `weightUnit` TEXT, `waistUnit` TEXT, `age` INTEGER NOT NULL, `height` REAL NOT NULL, `weight` REAL NOT NULL, `waist` REAL NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `NewExercise` (`detailLink` TEXT, `mainMuscleGroup` TEXT, `otherMuscleGroups` TEXT, `mainBodyGroup` TEXT, `tags` TEXT, `type` TEXT, `equipment` TEXT, `mechanics` TEXT, `difficulty` TEXT, `image1Link` TEXT, `image2Link` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT, `sets` TEXT, `repetitions` TEXT, `videoLink` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Language` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `languageCode` TEXT, `languageName` TEXT, `active` INTEGER)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `PlanWrapper` (`planType` TEXT, `plan` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `CategoryTypeProgress` (`mainMuscleGroup` TEXT NOT NULL, `exerciseId` INTEGER NOT NULL, `isCompleted` INTEGER NOT NULL, PRIMARY KEY(`mainMuscleGroup`, `exerciseId`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `ExerciseTranslation` (`languageId` INTEGER NOT NULL, `exerciseId` INTEGER NOT NULL, `exerciseTitle` TEXT, `detail` TEXT, PRIMARY KEY(`languageId`, `exerciseId`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"75ea037a2dee6fc1e6a5a269b3c91faa\")");
            }

            @Override
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `ExerciseProgress`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `UserAccount`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `NewExercise`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Language`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `PlanWrapper`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `CategoryTypeProgress`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `ExerciseTranslation`");
            }

            @Override
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                AppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                AppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override
            protected void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(6);
                hashMap.put(AppConstants.planIdKey, new TableInfo.Column(AppConstants.planIdKey, "INTEGER", true, 1));
                hashMap.put(AppConstants.weekIdKey, new TableInfo.Column(AppConstants.weekIdKey, "INTEGER", true, 2));
                hashMap.put(AppConstants.dayIdKey, new TableInfo.Column(AppConstants.dayIdKey, "INTEGER", true, 3));
                hashMap.put(AppConstants.exerciseIdKey, new TableInfo.Column(AppConstants.exerciseIdKey, "INTEGER", true, 4));
                hashMap.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0));
                hashMap.put("orderId", new TableInfo.Column("orderId", "INTEGER", true, 0));
                TableInfo tableInfo = new TableInfo("ExerciseProgress", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "ExerciseProgress");
                if (tableInfo.equals(read)) {
                    HashMap hashMap2 = new HashMap(10);
                    hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
                    hashMap2.put("name", new TableInfo.Column("name", "TEXT", false, 0));
                    hashMap2.put("image", new TableInfo.Column("image", "TEXT", false, 0));
                    hashMap2.put("heightUnit", new TableInfo.Column("heightUnit", "TEXT", false, 0));
                    hashMap2.put("weightUnit", new TableInfo.Column("weightUnit", "TEXT", false, 0));
                    hashMap2.put("waistUnit", new TableInfo.Column("waistUnit", "TEXT", false, 0));
                    hashMap2.put("age", new TableInfo.Column("age", "INTEGER", true, 0));
                    hashMap2.put("height", new TableInfo.Column("height", "REAL", true, 0));
                    hashMap2.put("weight", new TableInfo.Column("weight", "REAL", true, 0));
                    hashMap2.put("waist", new TableInfo.Column("waist", "REAL", true, 0));
                    TableInfo tableInfo2 = new TableInfo("UserAccount", hashMap2, new HashSet(0), new HashSet(0));
                    TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "UserAccount");
                    if (tableInfo2.equals(read2)) {
                        HashMap hashMap3 = new HashMap(15);
                        hashMap3.put("detailLink", new TableInfo.Column("detailLink", "TEXT", false, 0));
                        hashMap3.put("mainMuscleGroup", new TableInfo.Column("mainMuscleGroup", "TEXT", false, 0));
                        hashMap3.put("otherMuscleGroups", new TableInfo.Column("otherMuscleGroups", "TEXT", false, 0));
                        hashMap3.put("mainBodyGroup", new TableInfo.Column("mainBodyGroup", "TEXT", false, 0));
                        hashMap3.put("tags", new TableInfo.Column("tags", "TEXT", false, 0));
                        hashMap3.put("type", new TableInfo.Column("type", "TEXT", false, 0));
                        hashMap3.put("equipment", new TableInfo.Column("equipment", "TEXT", false, 0));
                        hashMap3.put("mechanics", new TableInfo.Column("mechanics", "TEXT", false, 0));
                        hashMap3.put("difficulty", new TableInfo.Column("difficulty", "TEXT", false, 0));
                        hashMap3.put("image1Link", new TableInfo.Column("image1Link", "TEXT", false, 0));
                        hashMap3.put("image2Link", new TableInfo.Column("image2Link", "TEXT", false, 0));
                        hashMap3.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
                        hashMap3.put("sets", new TableInfo.Column("sets", "TEXT", false, 0));
                        hashMap3.put("repetitions", new TableInfo.Column("repetitions", "TEXT", false, 0));
                        hashMap3.put("videoLink", new TableInfo.Column("videoLink", "TEXT", false, 0));
                        TableInfo tableInfo3 = new TableInfo("NewExercise", hashMap3, new HashSet(0), new HashSet(0));
                        TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "NewExercise");
                        if (tableInfo3.equals(read3)) {
                            HashMap hashMap4 = new HashMap(4);
                            hashMap4.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
                            hashMap4.put("languageCode", new TableInfo.Column("languageCode", "TEXT", false, 0));
                            hashMap4.put("languageName", new TableInfo.Column("languageName", "TEXT", false, 0));
                            hashMap4.put("active", new TableInfo.Column("active", "INTEGER", false, 0));
                            TableInfo tableInfo4 = new TableInfo("Language", hashMap4, new HashSet(0), new HashSet(0));
                            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "Language");
                            if (tableInfo4.equals(read4)) {
                                HashMap hashMap5 = new HashMap(3);
                                hashMap5.put("planType", new TableInfo.Column("planType", "TEXT", false, 0));
                                hashMap5.put("plan", new TableInfo.Column("plan", "TEXT", false, 0));
                                hashMap5.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
                                TableInfo tableInfo5 = new TableInfo("PlanWrapper", hashMap5, new HashSet(0), new HashSet(0));
                                TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "PlanWrapper");
                                if (tableInfo5.equals(read5)) {
                                    HashMap hashMap6 = new HashMap(3);
                                    hashMap6.put("mainMuscleGroup", new TableInfo.Column("mainMuscleGroup", "TEXT", true, 1));
                                    hashMap6.put(AppConstants.exerciseIdKey, new TableInfo.Column(AppConstants.exerciseIdKey, "INTEGER", true, 2));
                                    hashMap6.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0));
                                    TableInfo tableInfo6 = new TableInfo("CategoryTypeProgress", hashMap6, new HashSet(0), new HashSet(0));
                                    TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "CategoryTypeProgress");
                                    if (tableInfo6.equals(read6)) {
                                        HashMap hashMap7 = new HashMap(4);
                                        hashMap7.put("languageId", new TableInfo.Column("languageId", "INTEGER", true, 1));
                                        hashMap7.put(AppConstants.exerciseIdKey, new TableInfo.Column(AppConstants.exerciseIdKey, "INTEGER", true, 2));
                                        hashMap7.put("exerciseTitle", new TableInfo.Column("exerciseTitle", "TEXT", false, 0));
                                        hashMap7.put("detail", new TableInfo.Column("detail", "TEXT", false, 0));
                                        TableInfo tableInfo7 = new TableInfo("ExerciseTranslation", hashMap7, new HashSet(0), new HashSet(0));
                                        TableInfo read7 = TableInfo.read(supportSQLiteDatabase, "ExerciseTranslation");
                                        if (!tableInfo7.equals(read7)) {
                                            throw new IllegalStateException("Migration didn't properly handle ExerciseTranslation(com.rofitness.fitnessapp.database.faizanwork.entities.ExerciseTranslation).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
                                        }
                                        return;
                                    }
                                    throw new IllegalStateException("Migration didn't properly handle CategoryTypeProgress(com.rofitness.fitnessapp.database.entities.CategoryTypeProgress).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
                                }
                                throw new IllegalStateException("Migration didn't properly handle PlanWrapper(com.rofitness.fitnessapp.database.entities.PlanWrapper).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
                            }
                            throw new IllegalStateException("Migration didn't properly handle Language(com.rofitness.fitnessapp.database.faizanwork.entities.Language).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                        }
                        throw new IllegalStateException("Migration didn't properly handle NewExercise(com.rofitness.fitnessapp.database.faizanwork.entities.NewExercise).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                    }
                    throw new IllegalStateException("Migration didn't properly handle UserAccount(com.rofitness.fitnessapp.database.entities.UserAccount).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                throw new IllegalStateException("Migration didn't properly handle ExerciseProgress(com.rofitness.fitnessapp.database.entities.ExerciseProgress).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
        }, "75ea037a2dee6fc1e6a5a269b3c91faa", "2b59bd156639fde061a74586d7428d0f")).build());
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "ExerciseProgress", "UserAccount", "NewExercise", "Language", "PlanWrapper", "CategoryTypeProgress", "ExerciseTranslation");
    }

    @Override
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `ExerciseProgress`");
            writableDatabase.execSQL("DELETE FROM `UserAccount`");
            writableDatabase.execSQL("DELETE FROM `NewExercise`");
            writableDatabase.execSQL("DELETE FROM `Language`");
            writableDatabase.execSQL("DELETE FROM `PlanWrapper`");
            writableDatabase.execSQL("DELETE FROM `CategoryTypeProgress`");
            writableDatabase.execSQL("DELETE FROM `ExerciseTranslation`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override
    public ExerciseProgressDao getExerciseProgressDao() {
        ExerciseProgressDao exerciseProgressDao;
        if (this._exerciseProgressDao != null) {
            return this._exerciseProgressDao;
        }
        synchronized (this) {
            if (this._exerciseProgressDao == null) {
                this._exerciseProgressDao = new ExerciseProgressDao_Impl(this);
            }
            exerciseProgressDao = this._exerciseProgressDao;
        }
        return exerciseProgressDao;
    }

    @Override
    public UserAccountDao getUserAccountDao() {
        UserAccountDao userAccountDao;
        if (this._userAccountDao != null) {
            return this._userAccountDao;
        }
        synchronized (this) {
            if (this._userAccountDao == null) {
                this._userAccountDao = new UserAccountDao_Impl(this);
            }
            userAccountDao = this._userAccountDao;
        }
        return userAccountDao;
    }

    @Override
    public ExerciseDao getExerciseDao() {
        ExerciseDao exerciseDao;
        if (this._exerciseDao != null) {
            return this._exerciseDao;
        }
        synchronized (this) {
            if (this._exerciseDao == null) {
                this._exerciseDao = new ExerciseDao_Impl(this);
            }
            exerciseDao = this._exerciseDao;
        }
        return exerciseDao;
    }

    @Override
    public LanguageDao getLanguageDao() {
        LanguageDao languageDao;
        if (this._languageDao != null) {
            return this._languageDao;
        }
        synchronized (this) {
            if (this._languageDao == null) {
                this._languageDao = new LanguageDao_Impl(this);
            }
            languageDao = this._languageDao;
        }
        return languageDao;
    }

    @Override
    public PlanDao getPlanDao() {
        PlanDao planDao;
        if (this._planDao != null) {
            return this._planDao;
        }
        synchronized (this) {
            if (this._planDao == null) {
                this._planDao = new PlanDao_Impl(this);
            }
            planDao = this._planDao;
        }
        return planDao;
    }

    @Override
    public CategoryTypeProgressDao getCategoryTypeProgressDao() {
        CategoryTypeProgressDao categoryTypeProgressDao;
        if (this._categoryTypeProgressDao != null) {
            return this._categoryTypeProgressDao;
        }
        synchronized (this) {
            if (this._categoryTypeProgressDao == null) {
                this._categoryTypeProgressDao = new CategoryTypeProgressDao_Impl(this);
            }
            categoryTypeProgressDao = this._categoryTypeProgressDao;
        }
        return categoryTypeProgressDao;
    }

    @Override
    public ExerciseTranslationDao getExerciseTranslationDao() {
        ExerciseTranslationDao exerciseTranslationDao;
        if (this._exerciseTranslationDao != null) {
            return this._exerciseTranslationDao;
        }
        synchronized (this) {
            if (this._exerciseTranslationDao == null) {
                this._exerciseTranslationDao = new ExerciseTranslationDao_Impl(this);
            }
            exerciseTranslationDao = this._exerciseTranslationDao;
        }
        return exerciseTranslationDao;
    }
}
