import { Injectable } from '@angular/core';
import { environment } from '@app/../environments/environment';
import { Client } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebsocketService {
  client: Client;

  constructor() {}

  public async connect(): Promise<Client> {
    if (this.client) {
      return this.client;
    }
    return new Promise((resolve, reject) => {
      const client = new Client({
        brokerURL: environment.websocketUrl,
        onConnect: (frame) => {
          // generate new guid
          this.client = client;
          resolve(client);
        },
        onStompError: reject,
        onWebSocketError: reject,
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
      });
      client.activate();
    });
  }

  public subscribe(url: string, callback, header = {}) {
    return this.client.subscribe(
      url,
      (frame) => {
        const data = JSON.parse(frame.body);
        callback(data);
      },
      header
    );
  }

  send(url: string, body = {}) {
    this.client.publish({ destination: url, body: JSON.stringify(body) });
  }
}
