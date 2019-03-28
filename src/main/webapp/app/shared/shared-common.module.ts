import { NgModule } from '@angular/core';

import { TraderSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [TraderSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [TraderSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TraderSharedCommonModule {}
