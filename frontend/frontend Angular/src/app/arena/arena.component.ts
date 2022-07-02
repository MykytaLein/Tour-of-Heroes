import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-arena',
  templateUrl: './arena.component.html',
  styleUrls: ['./arena.component.css']
})
export class ArenaComponent implements OnInit {

  heroes: Hero[] = [];
  selectedHero1!: Hero;
  selectedHero2!: Hero;
  result: String | undefined;

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.getHeroes();
  }

  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes);
  }
  arena(id1: number, id2: number){

  }

  onSelect1(hero: Hero): void {
    if(this.selectedHero2!=hero){
      this.selectedHero1 = hero;
    }
  }

  onSelect2(hero: Hero): void {
    if(this.selectedHero1!=hero){
      this.selectedHero2 = hero;
    }
  }

  fight(sel1: Hero, sel2: Hero){
    if(sel1.strength>sel2.strength){
      this.result = this.selectedHero1?.name+' wins!';
    }else if (sel1.strength<sel2.strength){
      this.result = this.selectedHero2?.name+' wins!';
    }else{
      this.result = 'Nobody can win!';
    }
  }

}
