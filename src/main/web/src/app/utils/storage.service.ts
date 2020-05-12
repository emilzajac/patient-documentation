import { Injectable }   from '@angular/core';
import CryptoES         from 'crypto-es';
import { v4 as uuidv4 } from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private readonly cryptoKey: string;

  constructor() {
    this.cryptoKey = new Date().getTime().toString() + uuidv4();
  }

  public encryptData(data) {
    data = CryptoES.AES.encrypt(data, this.cryptoKey);
    data = data.toString();
    return data;
  }

  public decryptData(data) {
    data = CryptoES.AES.decrypt(data, this.cryptoKey);
    data = data.toString(CryptoES.enc.Utf8);
    return data;
  }

}
