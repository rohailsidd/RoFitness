package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> map) {
        this.creators = map;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> cls) {
        Provider<ViewModel> provider = this.creators.get(cls);
        if (provider == null) {
            Iterator<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> it = this.creators.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> next = it.next();
                if (cls.isAssignableFrom(next.getKey())) {
                    provider = next.getValue();
                    break;
                }
            }
        }
        if (provider != null) {
            try {
                return (T) provider.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("unknown model class " + cls);
        }
    }
}
