"use client"

import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { Providers } from './providers'
import { Grid } from "@chakra-ui/react";
import Navigation from "./components/navigation";
import Footer from "./components/footer";

const inter = Inter({ subsets: ["latin"] });

// export const metadata: Metadata = {
//   title: "Explore",
//   description: "Welcome to Explore",
// };

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang='en'>
      <body>
        <Providers>
          <Grid minHeight='100%' templateRows={'50px auto 120px'}>
            <Navigation />
            {children}
            <Footer />
          </Grid >
        </Providers>
      </body>
    </html>
  )
}
