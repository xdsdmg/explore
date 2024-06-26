"use client"

import { Box, Card, CardHeader, CardBody, CardFooter, HStack, Heading, Square, Icon, Text, Button } from "@chakra-ui/react"
import { FaHandsClapping } from "react-icons/fa6";

export default function Welcome(props: any) {
  return (
    <Box className="flex min-h-screen flex-col items-center justify-between p-24" bgGradient='linear(to-r, green.200, pink.500)'>
      <Card w='510px' variant='elevated' bg='gray.50'>
        <CardHeader>
          <HStack>
            <Heading size='md'>Hi, my friend. Welcome to Explore</Heading>
            <Square><Icon as={FaHandsClapping} color='orange' w={5} h={5} /></Square>
          </HStack>
        </CardHeader>
        <CardBody>
          <Text>
            We have sent an email to your mailbox, <Text as='i'>please click the link in the email to activate your account.</Text> If your account is already activated, please click the button below to jump to the login page.
          </Text>
        </CardBody>
        <CardFooter>
          {/*
            Submit button
          */}
          <Button type='submit' colorScheme='teal' variant='solid' width='100%' size='md' >
            Login
          </Button>
        </CardFooter>
      </Card>
    </Box>
  )
}