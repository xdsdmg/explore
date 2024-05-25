interface SingleResponseBodyIF {
  code: number,
  data: any,
  msg: string,
}

interface ArrayResponseBodyIF<T> {
  code: number,
  data: T[],
  total: number,
  msg: string,
}

export type {
  SingleResponseBodyIF,
  ArrayResponseBodyIF,
}