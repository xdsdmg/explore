"use client"

import { Box, Container, HStack, Text, Input, InputGroup, InputLeftElement, Button, Grid, GridItem, Center, Flex, Spacer, Heading, Link, Divider } from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";

export default function Home() {
  return (
    <Box>
      <Flex alignItems='center' gap='8' padding={'5px 150px'}>
        <Box p='2'>
          <Heading size='md'><Link>Explore</Link></Heading>
        </Box>
        <Spacer />
        <InputGroup w={250} size='sm'>
          <InputLeftElement pointerEvents='none'>
            <SearchIcon color='gray.600' />
          </InputLeftElement>
          <Input type='tel' placeholder='Search Explore' />
        </InputGroup>
        <Link>About Explore</Link>
        <Button size='md' variant='ghost'>Login</Button>
      </Flex>
      <Divider />
    </Box>
  );
}
