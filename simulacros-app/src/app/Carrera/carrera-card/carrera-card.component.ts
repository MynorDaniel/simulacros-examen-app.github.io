import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Carrera } from '../../../entities/Carrera';

@Component({
  selector: 'app-carrera-card',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './carrera-card.component.html',
  styleUrl: './carrera-card.component.css'
})
export class CarreraCardComponent {

  @Input({required: true}) carrera!: Carrera;
}