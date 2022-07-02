import { Component, OnInit, DoCheck } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit, DoCheck {
  heroes: Hero[] = [];
  
  constructor(private heroService: HeroService) { }
  
  ngDoCheck(): void {
    this.heroes.sort(function(x,y) {
      if (x.strength > y.strength) {
          return -1;
      }
      if (x.strength < y.strength) {
          return 1;
      }
      return 0;
    })  }

  ngOnInit(): void {
    this.getHeroes();
    
  }

  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes);
  }
}