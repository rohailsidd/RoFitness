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

import com.rofitness.fitnessapp.database.entities.UserAccount;

import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
public final class UserAccountDao_Impl implements UserAccountDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUserAccount;
    private final EntityInsertionAdapter __insertionAdapterOfUserAccount;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfUserAccount;

    public UserAccountDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUserAccount = new EntityInsertionAdapter<UserAccount>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `UserAccount`(`id`,`name`,`image`,`heightUnit`,`weightUnit`,`waistUnit`,`age`,`height`,`weight`,`waist`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, UserAccount userAccount) {
                supportSQLiteStatement.bindLong(1, (long) userAccount.getId());
                if (userAccount.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, userAccount.getName());
                }
                if (userAccount.getImage() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, userAccount.getImage());
                }
                if (userAccount.getHeightUnit() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, userAccount.getHeightUnit());
                }
                if (userAccount.getWeightUnit() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, userAccount.getWeightUnit());
                }
                if (userAccount.getWaistUnit() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, userAccount.getWaistUnit());
                }
                supportSQLiteStatement.bindLong(7, (long) userAccount.getAge());
                supportSQLiteStatement.bindDouble(8, userAccount.getHeight());
                supportSQLiteStatement.bindDouble(9, userAccount.getWeight());
                supportSQLiteStatement.bindDouble(10, userAccount.getWaist());
            }
        };
        this.__deletionAdapterOfUserAccount = new EntityDeletionOrUpdateAdapter<UserAccount>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `UserAccount` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, UserAccount userAccount) {
                supportSQLiteStatement.bindLong(1, (long) userAccount.getId());
            }
        };
        this.__updateAdapterOfUserAccount = new EntityDeletionOrUpdateAdapter<UserAccount>(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `UserAccount` SET `id` = ?,`name` = ?,`image` = ?,`heightUnit` = ?,`weightUnit` = ?,`waistUnit` = ?,`age` = ?,`height` = ?,`weight` = ?,`waist` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, UserAccount userAccount) {
                supportSQLiteStatement.bindLong(1, (long) userAccount.getId());
                if (userAccount.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, userAccount.getName());
                }
                if (userAccount.getImage() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, userAccount.getImage());
                }
                if (userAccount.getHeightUnit() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, userAccount.getHeightUnit());
                }
                if (userAccount.getWeightUnit() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, userAccount.getWeightUnit());
                }
                if (userAccount.getWaistUnit() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, userAccount.getWaistUnit());
                }
                supportSQLiteStatement.bindLong(7, (long) userAccount.getAge());
                supportSQLiteStatement.bindDouble(8, userAccount.getHeight());
                supportSQLiteStatement.bindDouble(9, userAccount.getWeight());
                supportSQLiteStatement.bindDouble(10, userAccount.getWaist());
                supportSQLiteStatement.bindLong(11, (long) userAccount.getId());
            }
        };
    }

    public void insert(UserAccount userAccount) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUserAccount.insert(/*(EntityInsertionAdapter)*/ userAccount);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(UserAccount... userAccountArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUserAccount.insert((Object[]) userAccountArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<UserAccount> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfUserAccount.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(UserAccount userAccount) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUserAccount.handle(userAccount);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(UserAccount userAccount) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfUserAccount.handle(userAccount);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public LiveData<UserAccount> getUserAccount() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM UserAccount WHERE id = 1", 0);
        return new ComputableLiveData<UserAccount>(this.__db.getQueryExecutor()) {
            private InvalidationTracker.Observer _observer;

            /* access modifiers changed from: protected */
            @Override
            public UserAccount compute() {
                UserAccount userAccount;
                if (this._observer == null) {
                    this._observer = new InvalidationTracker.Observer("UserAccount", new String[0]) {
                        @Override
                        public void onInvalidated(Set<String> set) {
                            invalidate();
                        }
                    };
                    UserAccountDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor query = UserAccountDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("name");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("image");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("heightUnit");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("weightUnit");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("waistUnit");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("age");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("height");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow("weight");
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("waist");
                    if (query.moveToFirst()) {
                        userAccount = new UserAccount();
                        userAccount.setId(query.getInt(columnIndexOrThrow));
                        userAccount.setName(query.getString(columnIndexOrThrow2));
                        userAccount.setImage(query.getString(columnIndexOrThrow3));
                        userAccount.setHeightUnit(query.getString(columnIndexOrThrow4));
                        userAccount.setWeightUnit(query.getString(columnIndexOrThrow5));
                        userAccount.setWaistUnit(query.getString(columnIndexOrThrow6));
                        userAccount.setAge(query.getInt(columnIndexOrThrow7));
                        userAccount.setHeight(query.getDouble(columnIndexOrThrow8));
                        userAccount.setWeight(query.getDouble(columnIndexOrThrow9));
                        userAccount.setWaist(query.getDouble(columnIndexOrThrow10));
                    } else {
                        userAccount = null;
                    }
                    return userAccount;
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
