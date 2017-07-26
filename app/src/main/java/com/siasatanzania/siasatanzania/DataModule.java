/*
 * Copyright (c) 2016 Ha Duy Trung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.siasatanzania.siasatanzania;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.siasatanzania.siasatanzania.accounts.UserServices;
import com.siasatanzania.siasatanzania.accounts.UserServicesClient;
import com.siasatanzania.siasatanzania.data.AlgoliaClient;
import com.siasatanzania.siasatanzania.data.AlgoliaPopularClient;
import com.siasatanzania.siasatanzania.data.FavoriteManager;
import com.siasatanzania.siasatanzania.data.FeedbackClient;
import com.siasatanzania.siasatanzania.data.HackerNewsClient;
import com.siasatanzania.siasatanzania.data.ItemManager;
import com.siasatanzania.siasatanzania.data.ReadabilityClient;
import com.siasatanzania.siasatanzania.data.SessionManager;
import com.siasatanzania.siasatanzania.data.SyncScheduler;
import com.siasatanzania.siasatanzania.data.UserManager;
import okhttp3.Call;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static com.siasatanzania.siasatanzania.ActivityModule.ALGOLIA;
import static com.siasatanzania.siasatanzania.ActivityModule.HN;
import static com.siasatanzania.siasatanzania.ActivityModule.POPULAR;

@Module(library = true, complete = false, includes = NetworkModule.class)
class DataModule {
    @Provides @Singleton @Named(HN)
    public ItemManager provideHackerNewsClient(HackerNewsClient client) {
        return client;
    }

    @Provides @Singleton @Named(ALGOLIA)
    public ItemManager provideAlgoliaClient(AlgoliaClient client) {
        return client;
    }

    @Provides @Singleton @Named(POPULAR)
    public ItemManager provideAlgoliaPopularClient(AlgoliaPopularClient client) {
        return client;
    }

    @Provides @Singleton
    public UserManager provideUserManager(HackerNewsClient client) {
        return client;
    }

    @Provides @Singleton
    public FeedbackClient provideFeedbackClient(FeedbackClient.Impl client) {
        return client;
    }

    @Provides @Singleton
    public ReadabilityClient provideReadabilityClient(ReadabilityClient.Impl client) {
        return client;
    }

    @Provides @Singleton
    public FavoriteManager provideFavoriteManager(Scheduler ioScheduler) {
        return new FavoriteManager(ioScheduler);
    }

    @Provides @Singleton
    public SessionManager provideSessionManager(Scheduler ioScheduler) {
        return new SessionManager(ioScheduler);
    }

    @Provides @Singleton
    public UserServices provideUserServices(Call.Factory callFactory, Scheduler ioScheduler) {
        return new UserServicesClient(callFactory, ioScheduler);
    }

    @Provides @Singleton
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides @Singleton
    public SyncScheduler provideSyncScheduler() {
        return new SyncScheduler();
    }
}
