import { Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { CursosComponent } from './cursos/cursos.component';
import { ReglasComponent } from './reglas/reglas.component';
import { ExamenComponent } from './examen/examen.component';

export const routes: Routes = [
    { path: '', component: MainComponent},
    { path: 'cursos', component: CursosComponent},
    { path: 'reglas', component: ReglasComponent},
    { path: 'examen/:curso/:tipo', component: ExamenComponent}
];