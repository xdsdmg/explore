"use client"

import { Box, Input, InputGroup, InputLeftElement, Button, Flex, Spacer, Heading, Link, Divider, VStack, Card, CardBody, CardFooter, Grid, Text } from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";

export default function Home() {
  return (
    <Grid minHeight='100%' templateRows={'60px auto 120px'}>
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
      <Flex justifyContent='center'>
        <VStack>
          <Card size='sm' w='800px'>
            <CardBody>This is the title of a topic</CardBody>
            <CardFooter>Hello world!</CardFooter>
          </Card>
          <Card size='sm' w='800px'>
            <CardBody>This is the title of a topic</CardBody>
            <CardFooter>Hello world!</CardFooter>
          </Card>
          <Card size='sm' w='800px'>
            <CardBody>This is the title of a topic</CardBody>
            <CardFooter>Hello world!</CardFooter>
          </Card>
        </VStack>
      </Flex>
      <Box>
        <Divider />
        <Flex justifyContent='center' >
          <VStack w='800px'>
            <Flex justifyContent='left' alignItems='center' w='100%' gap={4} padding={'5px 0 0 2px'}>
              <Link color='teal.500' as='b'>About</Link>
              <Link color='teal.500' as='b'>Help</Link>
            </Flex>
            <Box w='100%' padding={'2px 0 2px 0'}>
              <Text fontSize='sm'>Hi! Welcome to Explore.</Text>
              <Text fontSize='sm' as='cite'>In the world of computers, anyone who can program can own the world.</Text>
            </Box>
          </VStack>
        </Flex>
      </Box >
    </Grid >
  );
}
