package com.kakau.archive.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.kakau.archive.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class GameUiState(val games:List<GameEntity> = emptyList(), val search:String="", val darkTheme:Boolean=true, val selected:GameEntity?=null, val loading:Boolean=true)

class GameViewModel(app:Application):AndroidViewModel(app){
 private val db=Room.databaseBuilder(app,AppDatabase::class.java,"kakau_archive.db").fallbackToDestructiveMigration().build()
 private val repo=GameRepository(db.gameDao())
 private val search=MutableStateFlow("")
 private val dark=MutableStateFlow(true)
 private val selected=MutableStateFlow<GameEntity?>(null)
 private val loading=MutableStateFlow(true)
 val state:StateFlow<GameUiState> = combine(repo.games,search,dark,selected,loading){games,q,isDark,sel,busy->
  val filtered=if(q.isBlank()) games else games.filter{it.title.contains(q,true)||it.platform.contains(q,true)||it.genre.contains(q,true)}
  GameUiState(filtered,q,isDark,sel,busy)
 }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),GameUiState())
 init{ viewModelScope.launch{ SeedImporter.importIfEmpty(app,db.gameDao()); loading.value=false } }
 fun setSearch(v:String){search.value=v}
 fun toggleTheme(){dark.value=!dark.value}
 fun select(g:GameEntity?){selected.value=g}
 fun add(title:String,platform:String,genre:String)=viewModelScope.launch{ if(title.isNotBlank()) repo.add(GameEntity(title=title,normalizedTitle=com.kakau.archive.util.GameNormalizer.normalize(title),platform=platform,genre=genre)) }
}
