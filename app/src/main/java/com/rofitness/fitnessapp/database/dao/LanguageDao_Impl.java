package com.rofitness.fitnessapp.database.dao;

import android.database.Cursor;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.rofitness.fitnessapp.database.rohailwork.entities.Language;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public final class LanguageDao_Impl implements LanguageDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfLanguage;
    private final EntityInsertionAdapter __insertionAdapterOfLanguage;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfLanguage;

    public LanguageDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfLanguage = new EntityInsertionAdapter<Language>(roomDatabase) {
            @Override
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Language`(`id`,`languageCode`,`languageName`,`active`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Language language) {
                if (language.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, language.getId().longValue());
                }
                if (language.getLanguageCode() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, language.getLanguageCode());
                }
                if (language.getLanguageName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, language.getLanguageName());
                }
                Integer valueOf = language.getActive() == null ? null : Integer.valueOf(language.getActive().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, (long) valueOf.intValue());
                }
            }
        };
        this.__deletionAdapterOfLanguage = new EntityDeletionOrUpdateAdapter<Language>(roomDatabase) {
            @Override
            public String createQuery() {
                return "DELETE FROM `Language` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Language language) {
                if (language.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, language.getId().longValue());
                }
            }
        };
        this.__updateAdapterOfLanguage = new EntityDeletionOrUpdateAdapter<Language>(roomDatabase) {
            @Override
            public String createQuery() {
                return "UPDATE OR REPLACE `Language` SET `id` = ?,`languageCode` = ?,`languageName` = ?,`active` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Language language) {
                if (language.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, language.getId().longValue());
                }
                if (language.getLanguageCode() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, language.getLanguageCode());
                }
                if (language.getLanguageName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, language.getLanguageName());
                }
                Integer valueOf = language.getActive() == null ? null : Integer.valueOf(language.getActive().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, (long) valueOf.intValue());
                }
                if (language.getId() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, language.getId().longValue());
                }
            }
        };
    }

    public void insert(Language language) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLanguage.insert(/*(EntityInsertionAdapter) */language);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(Language... languageArr) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLanguage.insert((Object[]) languageArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<Long> insert(List<Language> list) {
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfLanguage.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(Language language) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfLanguage.handle(language);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(Language language) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfLanguage.handle(language);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override
    public List<String> getLanguages() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT languageName FROM Language", 0);
        Cursor query = this.__db.query(acquire);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Language getLanguage(String str) {
        Long l;
        Integer num;
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM Language where languageName =?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("languageCode");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("languageName");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("active");
            Language language = null;
            Boolean bool = null;
            if (query.moveToFirst()) {
                Language language2 = new Language();
                if (query.isNull(columnIndexOrThrow)) {
                    l = null;
                } else {
                    l = Long.valueOf(query.getLong(columnIndexOrThrow));
                }
                language2.setId(l);
                language2.setLanguageCode(query.getString(columnIndexOrThrow2));
                language2.setLanguageName(query.getString(columnIndexOrThrow3));
                if (query.isNull(columnIndexOrThrow4)) {
                    num = null;
                } else {
                    num = Integer.valueOf(query.getInt(columnIndexOrThrow4));
                }
                if (num != null) {
                    if (num.intValue() == 0) {
                        z = false;
                    }
                    bool = Boolean.valueOf(z);
                }
                language2.setActive(bool);
                language = language2;
            }
            return language;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override
    public Language getLanguageByCode(String str) {
        Long l;
        Integer num;
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM Language where languageCode like ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.__db.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("languageCode");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("languageName");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("active");
            Language language = null;
            Boolean bool = null;
            if (query.moveToFirst()) {
                Language language2 = new Language();
                if (query.isNull(columnIndexOrThrow)) {
                    l = null;
                } else {
                    l = Long.valueOf(query.getLong(columnIndexOrThrow));
                }
                language2.setId(l);
                language2.setLanguageCode(query.getString(columnIndexOrThrow2));
                language2.setLanguageName(query.getString(columnIndexOrThrow3));
                if (query.isNull(columnIndexOrThrow4)) {
                    num = null;
                } else {
                    num = Integer.valueOf(query.getInt(columnIndexOrThrow4));
                }
                if (num != null) {
                    if (num.intValue() == 0) {
                        z = false;
                    }
                    bool = Boolean.valueOf(z);
                }
                language2.setActive(bool);
                language = language2;
            }
            return language;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
