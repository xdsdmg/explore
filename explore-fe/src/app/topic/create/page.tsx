/// Markdown editor: https://github.com/uiwjs/react-markdown-editor

"use client"

import {
  Box, Card, Divider, Text, Button
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
  const MarkdownEditor = dynamic(
    () => import("@uiw/react-markdown-editor").then((mod) => mod.default),
    { ssr: false }
  );

  return (
    <Box className="flex flex-col items-center justify-between p-24" >
      <Card w='800px' minH='500px' variant='outline'>
        <Box h='30px' lineHeight='30px' paddingLeft='10px'><Text as='b'>Create a new topic</Text></Box>
        <Divider />
        <Box padding={1}><MarkdownEditor height='460px' value="Hello Markdown!" /></Box>
        <Divider />
        <Box padding={1}>
          <Button colorScheme='teal' size='sm' w='120px' variant='outline'>Publish</Button>
        </Box>
      </Card>
    </Box >
  );
}