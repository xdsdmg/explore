function range(begin: number, end: number): number[] {
  if (end <= begin) {
    return [];
  }

  let nums: number[] = [];

  for (let i = begin; i < end; i++) {
    nums.push(i);
  }

  return nums;
}

export { range }