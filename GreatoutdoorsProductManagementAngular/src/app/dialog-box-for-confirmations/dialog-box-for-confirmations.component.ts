import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-box-for-confirmations',
  templateUrl: './dialog-box-for-confirmations.component.html',
  styleUrls: ['./dialog-box-for-confirmations.component.css']
})
export class DialogBoxForConfirmationsComponent implements OnInit {

  constructor(   public dialogRef: MatDialogRef<DialogBoxForConfirmationsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }
  
  onNoClick(): void {
    this.dialogRef.close(false);
  }
  onYesClick()
  {
    this.dialogRef.close(true);
  }
}
