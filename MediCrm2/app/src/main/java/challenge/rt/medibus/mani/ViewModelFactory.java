package challenge.rt.medibus.mani;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.VisibleForTesting;
import challenge.rt.medibus.mani.data.source.SalesRepDataSource;
import challenge.rt.medibus.mani.data.source.TaskDataSource;
import challenge.rt.medibus.mani.data.source.local.UserDatabase;
import challenge.rt.medibus.mani.ui.home.HomeViewModel;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateViewModel;
import challenge.rt.medibus.mani.ui.taskscreate.TaskCreateViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak") private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final SalesRepDataSource mSalesDataSource;

    private final TaskDataSource mTaskDataSource;

    private UserDatabase mUserDatabase;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application, Injection.provideSalesRepLocalDataSource(application),
                        Injection.provideTaskLocalDataSource(application));
                    INSTANCE.mUserDatabase = Injection.provideUserDatabase(application);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, SalesRepDataSource repository, TaskDataSource taskDataSource) {

        mApplication = application;

        mSalesDataSource = repository;

        mTaskDataSource = taskDataSource;
    }

    @Override public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            //noinspection unchecked
            return (T) new HomeViewModel(mApplication, mSalesDataSource, mTaskDataSource);
        } else if (modelClass.isAssignableFrom(SalesCreateViewModel.class)) {

            //noinspection unchecked
            return (T) new SalesCreateViewModel(mApplication, mSalesDataSource);
        } else if (modelClass.isAssignableFrom(TaskCreateViewModel.class)) {

            //noinspection unchecked
            return (T) new TaskCreateViewModel(mApplication, mTaskDataSource);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}