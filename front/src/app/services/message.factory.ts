import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root',
})
export class MessageFactory {
  /**
   * This Services works as a factory of message.
   * So, you do not need to create every message by setting all parameters in its constructor.
   * In fact, this service will do it for you.
   */
  constructor(private messageService: MessageService) {}

  public sendSuccessMessage(summary: string, detail: string) {
    this.messageService.add({
      severity: 'success',
      summary,
      detail,
    });
  }

  public sendFailureMessage(summary: string, detail: string) {
    this.messageService.add({
      severity: 'success',
      summary,
      detail,
    });
  }
}
