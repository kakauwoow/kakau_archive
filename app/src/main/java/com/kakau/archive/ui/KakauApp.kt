package com.kakau.archive.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kakau.archive.data.GameEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun KakauApp(vm:GameViewModel){
 val s by vm.state.collectAsState(); var add by remember{mutableStateOf(false)}
 s.selected?.let{g-> BackHandler{vm.select(null)}; Scaffold(topBar={TopAppBar(title={Text(g.title)},navigationIcon={IconButton(onClick={vm.select(null)}){Icon(Icons.Default.ArrowBack,"Back")}})}){p->Column(Modifier.padding(p).padding(20.dp)){Text(g.title,style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold);Spacer(Modifier.height(12.dp));Text("Platform: ${g.platform.ifBlank{"—"}}");Text("Genre: ${g.genre.ifBlank{"—"}}");Text("Year: ${g.yearReleased.ifBlank{"—"}}");Text("Developer: ${g.developer.ifBlank{"—"}}");Text("Publisher: ${g.publisher.ifBlank{"—"}}")}}; return }
 Scaffold(topBar={TopAppBar(title={Text("کاکائو آرشیو",fontWeight=FontWeight.Bold)},actions={IconButton(onClick=vm::toggleTheme){Icon(if(s.darkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,null)};IconButton(onClick={add=true}){Icon(Icons.Default.Add,"Add")}})}){p->
  Column(Modifier.padding(p).fillMaxSize()){
   OutlinedTextField(s.search,vm::setSearch,Modifier.fillMaxWidth().padding(12.dp),singleLine=true,leadingIcon={Icon(Icons.Default.Search,null)},placeholder={Text("Search games")})
   if(s.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
   LazyVerticalGrid(columns=GridCells.Adaptive(145.dp),contentPadding=PaddingValues(12.dp),horizontalArrangement=Arrangement.spacedBy(10.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){
    items(s.games,key={it.id}){g->GameCard(g){vm.select(g)}}
   }
  }
 }
 if(add) AddDialog(onDismiss={add=false}){t,p,g->vm.add(t,p,g);add=false}
}

@Composable private fun GameCard(g:GameEntity,onClick:()->Unit){ElevatedCard(onClick=onClick){Column(Modifier.padding(14.dp)){Icon(Icons.Default.SportsEsports,null);Spacer(Modifier.height(10.dp));Text(g.title,fontWeight=FontWeight.SemiBold);if(g.platform.isNotBlank())Text(g.platform,style=MaterialTheme.typography.labelMedium)}}}

@Composable private fun AddDialog(onDismiss:()->Unit,onSave:(String,String,String)->Unit){var t by remember{mutableStateOf("")};var p by remember{mutableStateOf("")};var g by remember{mutableStateOf("")};AlertDialog(onDismissRequest=onDismiss,title={Text("Add Game")},text={Column(verticalArrangement=Arrangement.spacedBy(8.dp)){OutlinedTextField(t,{t=it},label={Text("Title")});OutlinedTextField(p,{p=it},label={Text("Platform")});OutlinedTextField(g,{g=it},label={Text("Genre")})}},confirmButton={Button(onClick={onSave(t,p,g)},enabled=t.isNotBlank()){Text("Save")}},dismissButton={TextButton(onClick=onDismiss){Text("Cancel")}})}
