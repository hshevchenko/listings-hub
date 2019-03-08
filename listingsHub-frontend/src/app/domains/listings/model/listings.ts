export class Listing{
  id: string;
  name: string;
  category: string;
  description: string;
  address: Address = new Address();
  products: Array<Product> = new Array<Product>();
}

export class Address{
  addressLine1: string;
  city: string;
  country: string;
  postalCode: string;

}

export class Product{
  id: string;
  name: string;
  description: string;
  price: number;
}


export class ListingsFilterCriteria{
  name: string;
  category: string;
  address: Address;
}
