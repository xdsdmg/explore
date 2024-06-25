/// Markdown editor: https://github.com/uiwjs/react-markdown-editor

"use client"

import {
  Box, Card, Divider, Text, Button,
  FormControl, InputGroup, Input, FormLabel
}
  from '@chakra-ui/react';

import { defineStyle, defineStyleConfig } from '@chakra-ui/react'
import dynamic from 'next/dynamic';
import '@uiw/react-markdown-editor/markdown-editor.css';
import '@uiw/react-markdown-preview/markdown.css';

const brandPrimary = defineStyle({
  border: 'none',
})

export const codeTheme = defineStyleConfig({
  variants: { brandPrimary },
})

export default function Create() {
  let mdContent = '';

  const MarkdownEditor = dynamic(
    () => import("@uiw/react-markdown-editor").then((mod) => mod.default),
    { ssr: false }
  );

  const handlePublish = () => {
    console.log(mdContent);
    let title = (document.getElementById('title') as HTMLInputElement).value;
    console.log(title);
  };

  return (
    <Box className="flex flex-col items-center justify-between p-24" >
      <Card w='800px' minH='500px' variant='outline'>
        <Box h='30px' lineHeight='30px' paddingLeft={1}><Text as='b'>Create a new topic</Text></Box>
        <Divider />
        <Box padding={1}>
          <FormControl isRequired >
            <FormLabel htmlFor='title' fontSize={15}>Title</FormLabel>
            <InputGroup>
              <Input id='title' type='text' placeholder='Title' size='sm' marginBottom={1} />
            </InputGroup>
          </FormControl>
          <FormControl isRequired >
            <FormLabel htmlFor='content' fontSize={15}>Content</FormLabel>
            <MarkdownEditor
              id='content'
              height='460px'
              value='Hello Markdown!'
              onChange={(value, viewUpdate) => {
                mdContent = value;
              }}
            />
          </FormControl>
        </Box>
        <Divider />
        <Box padding={1}>
          <Button colorScheme='teal' size='sm' w='120px' variant='outline' onClick={handlePublish}>Publish</Button>
        </Box>
      </Card>
    </Box >
  );
}