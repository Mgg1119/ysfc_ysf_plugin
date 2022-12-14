//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fiveelements.unpay.xp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressLint({"ApplySharedPref"})
public final class SPUtils {
    private static final Map<String, SPUtils> SP_UTILS_MAP = new HashMap();
    private SharedPreferences sp;

    public static SPUtils getInstance(Activity activity) {
        return getInstance("", 0, activity);
    }

    public static SPUtils getInstance(int mode, Activity activity) {
        return getInstance("", mode, activity);
    }

    public static SPUtils getInstance(String spName, Activity activity) {
        return getInstance(spName, 0, activity);
    }

    public static SPUtils getInstance(String spName, int mode, Activity activity) {
        if (isSpace(spName)) {
            spName = "spUtils";
        }

        SPUtils spUtils = (SPUtils) SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            Class var3 = SPUtils.class;
            synchronized (SPUtils.class) {
                spUtils = (SPUtils) SP_UTILS_MAP.get(spName);
                if (spUtils == null) {
                    spUtils = new SPUtils(spName, mode, activity);
                    SP_UTILS_MAP.put(spName, spUtils);
                }
            }
        }

        return spUtils;
    }

    private SPUtils(String spName, Activity activity) {
        this.sp = activity.getSharedPreferences(spName, 0);
    }

    private SPUtils(String spName, int mode, Activity activity) {
        this.sp = activity.getSharedPreferences(spName, mode);
    }

    public void put(@NonNull String key, String value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, false);
        }
    }

    public void put(@NonNull String key, String value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putString(key, value).commit();
            } else {
                this.sp.edit().putString(key, value).apply();
            }

        }
    }

    public String getString(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getString(key, "");
        }
    }

    public String getString(@NonNull String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getString(key, defaultValue);
        }
    }

    public void put(@NonNull String key, int value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, false);
        }
    }

    public void put(@NonNull String key, int value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putInt(key, value).commit();
            } else {
                this.sp.edit().putInt(key, value).apply();
            }

        }
    }

    public int getInt(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getInt(key, -1);
        }
    }

    public int getInt(@NonNull String key, int defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getInt(key, defaultValue);
        }
    }

    public void put(@NonNull String key, long value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, false);
        }
    }

    public void put(@NonNull String key, long value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putLong(key, value).commit();
            } else {
                this.sp.edit().putLong(key, value).apply();
            }

        }
    }

    public long getLong(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getLong(key, -1L);
        }
    }

    public long getLong(@NonNull String key, long defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getLong(key, defaultValue);
        }
    }

    public void put(@NonNull String key, float value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, false);
        }
    }

    public void put(@NonNull String key, float value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putFloat(key, value).commit();
            } else {
                this.sp.edit().putFloat(key, value).apply();
            }

        }
    }

    public float getFloat(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getFloat(key, -1.0F);
        }
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getFloat(key, defaultValue);
        }
    }

    public void put(@NonNull String key, boolean value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, false);
        }
    }

    public void put(@NonNull String key, boolean value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putBoolean(key, value).commit();
            } else {
                this.sp.edit().putBoolean(key, value).apply();
            }

        }
    }

    public boolean getBoolean(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getBoolean(key, false);
        }
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getBoolean(key, defaultValue);
        }
    }

    public void put(@NonNull String key, Set<String> value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, false);
        }
    }

    public void put(@NonNull String key, Set<String> value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putStringSet(key, value).commit();
            } else {
                this.sp.edit().putStringSet(key, value).apply();
            }

        }
    }

    public Set<String> getStringSet(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getStringSet(key, Collections.emptySet());
        }
    }

    public Set<String> getStringSet(@NonNull String key, Set<String> defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getStringSet(key, defaultValue);
        }
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public boolean contains(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.contains(key);
        }
    }

    public void remove(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.remove(key, false);
        }
    }

    public void remove(@NonNull String key, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().remove(key).commit();
            } else {
                this.sp.edit().remove(key).apply();
            }

        }
    }

    public void clear() {
        this.clear(false);
    }

    public void clear(boolean isCommit) {
        if (isCommit) {
            this.sp.edit().clear().commit();
        } else {
            this.sp.edit().clear().apply();
        }

    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        } else {
            int i = 0;

            for (int len = s.length(); i < len; ++i) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
