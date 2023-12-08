import {Directive, ElementRef, Renderer2} from '@angular/core';

@Directive({
  selector: '[appErrorDirective]',
  standalone: true
})
export class AppErrorDirective {

  constructor(private elRef: ElementRef, private renderer: Renderer2) {
    this.renderer.setStyle(this.elRef.nativeElement, "color", "red");
  }

}
