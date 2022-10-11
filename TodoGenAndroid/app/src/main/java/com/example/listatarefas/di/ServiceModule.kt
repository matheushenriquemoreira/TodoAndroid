package com.example.listatarefas.di

import com.example.listatarefas.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
//SINGLETON -> OBJETO SERÁ ÚNICO!
@InstallIn(SingletonComponent::class)
object ServiceModule {

    //CRIAR DENTRO DE OBJECT MÉTODO PARA PROVER A CLASSE PARA GENTE
    @Singleton
    @Provides
    fun returnRepository(): Repository{
        return Repository()
    }


}