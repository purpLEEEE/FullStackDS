import {Directive, ElementRef, Input, Renderer2} from '@angular/core';

@Directive({
  selector: '[appColorDirective]',
  standalone: true
})
export class ColorDirective {

  @Input()
  set appColorDirective(i: string){
    if(parseInt(i)>2){
      this._renderer.setStyle(this._el.nativeElement, "color", "yellow")
    } else if (parseInt(i) <= 1){
      this._renderer.setStyle(this._el.nativeElement, "color", "red")
    }
  }

  constructor(private _el:ElementRef, private _renderer:Renderer2) { }

}
