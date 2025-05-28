
export interface OhosResourceService {

  getString(resName: string): string;

  getString(resName: string,  args: Array<string | number>): string;

  getPlural(resName: string, args: number): string;

  getImage(resName: string): ArrayBuffer;

  getColor(resName: string): number;

  getImageBase64(resName: string): string;

  getFile(resName: string): Uint8Array;

}