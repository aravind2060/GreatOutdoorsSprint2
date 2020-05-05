import { Pipe, PipeTransform } from '@angular/core';
import { ProductDTO } from '../Model/ProductDTO'

@Pipe({
  name: 'filterproduct'
})
export class FilterproductPipe implements PipeTransform {

  transform(items: ProductDTO[], searchValue: string): ProductDTO[] {
    if (searchValue == "" || searchValue == null)
      return items;
    else {
      searchValue = searchValue.toLowerCase();
      return items.filter(
        itm => {
          return itm.productName.toLowerCase().startsWith(searchValue) ? itm.productName.toLowerCase() : null;
        }
      );
    }

  }


}
