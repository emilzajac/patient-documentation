import { Injectable } from '@angular/core';
import CryptoES       from 'crypto-es';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  private readonly uuid: string;

  constructor() {
    let date = new Date();
    this.uuid = date.getFullYear().toString() + date.getDate().toString() + date.toDateString();
  }

  set(key: string, value: any) {
    key = this.hash(key);
    value = this.encryptData(JSON.stringify(value));
    sessionStorage.setItem(key, value);
  }

  get(key: string): any {
    key = this.hash(key);
    let value = sessionStorage.getItem(key);
    if (!value) {
      return value;
    }
    value = this.decryptData(value);
    return JSON.parse(value);
  }

  remove(key: string) {
    key = this.decryptData(key);
    sessionStorage.removeItem(key);
  }

  clear() {
    sessionStorage.clear();
  }

  private encryptData(data): string {
    return CryptoES.AES.encrypt(data, this.uuid).toString();
  }

  private decryptData(data): string {
    return CryptoES.AES.decrypt(data, this.uuid).toString(CryptoES.enc.Utf8);
  }

  private hash(key: string): string {
    return CryptoES.HmacSHA512(key, this.uuid).toString();
  }

}
