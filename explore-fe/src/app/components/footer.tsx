/**
 * Footer
 */

import {
  Box, Flex, Link, Divider, VStack, Text
} from "@chakra-ui/react";

export default function Footer() {
  return (
    <Box>
      <Divider color='gray' colorScheme='gray' />
      <Flex justifyContent='center' bg='white'>
        <VStack w='800px'>
          <Flex justifyContent='left' alignItems='center' w='100%' gap={4} padding={'5px 0 0 2px'}>
            <Link color='gray.500' as='b'>About</Link>
            <Link color='gray.500' as='b'>Help</Link>
          </Flex>
          <Box w='100%' padding={'2px 0 2px 0'}>
            <Text fontSize='sm'>Hi! Welcome to Explore.</Text>
            <Text fontSize='sm' as='cite'>Do have faith in what you love.</Text>
          </Box>
        </VStack>
      </Flex>
    </Box >
  )
}