import { registerServiceProvider,invoke } from '@kuiklybase/knoi';
import { OhosResourceService } from './KmmResource';

export class OhosResourceServiceImpl implements OhosResourceService {
  static context: Context

  getString(resName: string): string;

  getString(resName: string, args: Array<string | number>): string;

  getString(resName: string, args?: Array<string | number>): string {
    if (args != null && args.length > 0) {
      return OhosResourceServiceImpl.context.resourceManager.getStringByNameSync(resName, ...args)
    } else {
      return OhosResourceServiceImpl.context.resourceManager.getStringByNameSync(resName)
    }
  }


  getPlural(resName: string, args: number): string {
    return OhosResourceServiceImpl.context.resourceManager.getPluralStringByNameSync(resName, args)
  }

  getImage(resName: string): Uint8Array {
    return OhosResourceServiceImpl.context.resourceManager.getMediaByNameSync(resName)
  }

  getColor(resName: string): number {
    return OhosResourceServiceImpl.context.resourceManager.getColorByNameSync(resName)
  }

  getImageBase64(resName: string): string {
    return OhosResourceServiceImpl.context.resourceManager.getMediaBase64ByNameSync(resName)
  }

  getFile(resName: string): Uint8Array {
    return OhosResourceServiceImpl.context.resourceManager.getRawFileContentSync(resName)
  }
}

export function initResourceCompose(context: Context) {
  OhosResourceServiceImpl.context = context.getApplicationContext()
  invoke("init_resource_compose",context.resourceManager)
  registerServiceProvider("OhosResourceService", /** 是否单例 **/ true, OhosResourceServiceImpl)
}
