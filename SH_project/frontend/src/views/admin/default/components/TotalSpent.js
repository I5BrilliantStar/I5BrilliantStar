import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Button,
  Flex,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import Card from "components/card/Card.js";

export default function TotalSpent(props) {
  const { ...rest } = props;
  const [fetchImage, setFetchImage] = useState(true);
  const [data, setData] = useState({
    qr_data: "",
    qr_image: "",
    label: "",
  });

  const toggleFetchImage = () => {
    setFetchImage((prevState) => !prevState);
  };

  useEffect(() => {
    const fetchData = async () => {
      if (fetchImage) {
        try {
          const response = await axios.get("http://10.10.10.111:5000/stream");
          setData(response.data);
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      }
    };

    const intervalId = setInterval(fetchData, 33);

    return () => {
      clearInterval(intervalId);
    };
  }, [fetchImage]);

  const textColor = useColorModeValue("secondaryGray.900", "white");

  return (
    <Card
      justifyContent="center"
      align="center"
      direction="column"
      w="100%"
      mb="0px"
      {...rest}
    >
      <Flex align="center" w="100%" px="15px" py="10px">
        <Text
          me="auto"
          color={textColor}
          fontSize="xl"
          fontWeight="700"
          lineHeight="100%"
        >
          AICamera
        </Text>
      </Flex>
      <Flex justify="space-between" ps="0px" pe="20px" pt="5px">
        <Flex align="center" w="100%">
          <div className="TotalSpent">
            <img
              src={"data:image/jpeg;base64," + data.qr_image}
              alt="Video Stream"
              style={{ maxWidth: "100%", height: "auto" }}
            />
          </div>
        </Flex>
      </Flex>
      <Flex w="100%" flexDirection={{ base: "column", lg: "row" }}>
        <Flex flexDirection="column" me="20px" mt="28px">
          <Flex align="center" mb="20px">
            <Button
              backgroundColor="brand.500"
              color="white"
              fontSize="sm"
              fontWeight="500"
              mt="4px"
              mr="12px"
              _hover={{ backgroundColor: "brand.600" }}
              onClick={toggleFetchImage}
            >
              {fetchImage ? "Stop Camera" : "Start Camera"}
            </Button>
          </Flex>
        </Flex>
      </Flex>
    </Card>
  );
}
