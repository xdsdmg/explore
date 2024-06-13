/// Code editor with highlight: https://github.com/react-simple-code-editor/react-simple-code-editor

"use client"

import {
  Box, Card, Divider, Text, Textarea,
  Tabs, TabList, Tab, TabPanels, TabPanel,
  Code,
}
  from '@chakra-ui/react';

import { defineStyle, defineStyleConfig } from '@chakra-ui/react'

const brandPrimary = defineStyle({
  border: 'none',
})

export const codeTheme = defineStyleConfig({
  variants: { brandPrimary },
})

const codeBlock = `
const GroceryItem: React.FC<GroceryItemProps> = ({ item }) => {
  return (
    <div>
      <h2>{item.name}</h2>
      <p>Price: {item.price}</p>
      <p>Quantity: {item.quantity}</p>
    </div>
  );
}
`

export default function Create() {
  return (
    <Box className="flex flex-col items-center justify-between p-24" >
      <Card w='800px' minH='500px' variant='outline'>
        <Box h='40px' lineHeight='40px' paddingLeft='10px'><Text as='b'>Create a new topic</Text></Box>
        <Divider />
        <Tabs>
          <TabList>
            <Tab>Content</Tab>
            <Tab>Preview</Tab>
          </TabList>
          <TabPanels>
            <TabPanel>
              <Code w='100%' variant='brandPrimary'>
                <Textarea size='sm' ></Textarea>
              </Code>
            </TabPanel>
            <TabPanel>World</TabPanel>
          </TabPanels>
        </Tabs>
      </Card>
    </Box >
  );
}