import React, { useMemo, useState, useEffect } from "react";
import {
  useGlobalFilter,
  usePagination,
  useSortBy,
  useTable,
} from "react-table";
import {
  Flex,
  Table,
  Checkbox,
  Tbody,
  Td,
  Text,
  Th,
  Thead,
  Tr,
  useColorModeValue,
  Button,
} from "@chakra-ui/react";
import axios from "axios";

// Custom components
import Card from "components/card/Card";
import Menu from "components/menu/MainMenu";

export default function CheckTable() {
  const columnsData = useMemo(
    () => [
      {
        Header: "NAME",
        accessor: "name",
      },
      {
        Header: "PRICE", // "progress"를 "price"로 변경
        accessor: "price", // "progress"를 "price"로 변경
      },
      {
        Header: "QUANTITY",
        accessor: "quantity",
      },
      {
        Header: "DATE",
        accessor: "date",
      },
    ],
    []
  );

  const [tableData, setTableData] = useState([
    {
      name: "테스트 상품",
      price: 100,
      quantity: 10,
      date: "2023-09-13",
    },
  ]); // 상품 목록에 하나의 항목 추가
  const [selectedRows, setSelectedRows] = useState([]); // 체크된 행을 관리하는 상태

  
 /*  useEffect(() => {
    // API 요청을 보내 데이터 가져오기
    axios.get("/api/products").then((response) => {
      setTableData(response.data);
    });
  }, []); */

  const columns = useMemo(() => columnsData, [columnsData]);
  const data = useMemo(() => tableData, [tableData]);

  const tableInstance = useTable(
    {
      columns,
      data,
    },
    useGlobalFilter,
    useSortBy,
    usePagination
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    page,
    prepareRow,
    initialState,
  } = tableInstance;
  initialState.pageSize = 11;

  const textColor = useColorModeValue("secondaryGray.900", "white");
  const borderColor = useColorModeValue("gray.200", "whiteAlpha.100");

  // 체크박스 상태를 토글하는 함수
  const toggleRowSelection = (row) => {
    const isSelected = selectedRows.includes(row);
    if (isSelected) {
      setSelectedRows(selectedRows.filter((selectedRow) => selectedRow !== row));
    } else {
      setSelectedRows([...selectedRows, row]);
    }
  };

  


  // 선택된 행을 삭제하는 함수
  const deleteSelectedRows = () => {
    // 선택된 행을 삭제하는 로직을 구현
    const updatedTableData = tableData.filter((row) => !selectedRows.includes(row));
    setTableData(updatedTableData);
    setSelectedRows([]); // 선택된 행 초기화
  };

  return (
    <Card
      direction="column"
      w="100%"
      px="0px"
      overflowX={{ sm: "scroll", lg: "hidden" }}
    >
      <Flex px="25px" justify="space-between" mb="20px" align="center">
        <Text
          color={textColor}
          fontSize="22px"
          fontWeight="700"
          lineHeight="100%"
        >
          Check Table
        </Text>
        <Menu />
      </Flex>
      <Flex justify="space-between" align="center">
      <Table {...getTableProps()} variant="simple" color="gray.500" mb="24px">
        <Thead>
          {headerGroups.map((headerGroup, index) => (
            <Tr {...headerGroup.getHeaderGroupProps()} key={index}>
              {headerGroup.headers.map((column, index) => (
                <Th
                  {...column.getHeaderProps(column.getSortByToggleProps())}
                  pe="10px"
                  key={index}
                  borderColor={borderColor}
                >
                  <Flex
                    justify="space-between"
                    align="center"
                    fontSize={{ sm: "10px", lg: "12px" }}
                    color="gray.400"
                  >
                    {column.render("Header")}
                  </Flex>
                </Th>
              ))}
            </Tr>
          ))}
        </Thead>
        <Tbody {...getTableBodyProps()}>
          {page.map((row, index) => {
            prepareRow(row);
            const isSelected = selectedRows.includes(row.original);
            return (
              <Tr {...row.getRowProps()} key={index}>
                <Td>
                  <Checkbox
                    defaultChecked={isSelected}
                    colorScheme="brandScheme"
                    onChange={() => toggleRowSelection(row.original)}
                  />
                </Td>
                {row.cells.map((cell, index) => {
                  let data = "";
                  if (cell.column.Header === "NAME") {
                    data = (
                      <Flex align="center">
                        <Text color={textColor} fontSize="sm" fontWeight="700">
                          {cell.value}
                        </Text>
                      </Flex>
                    );
                  } else if (cell.column.Header === "PRICE") {
                    data = (
                      <Flex align="center">
                        <Text
                          me="10px"
                          color={textColor}
                          fontSize="sm"
                          fontWeight="700"
                        >
                          {cell.value}
                        </Text>
                      </Flex>
                    );
                  } else if (cell.column.Header === "QUANTITY") {
                    data = (
                      <Text color={textColor} fontSize="sm" fontWeight="700">
                        {cell.value}
                      </Text>
                    );
                  } else if (cell.column.Header === "DATE") {
                    data = (
                      <Text color={textColor} fontSize="sm" fontWeight="700">
                        {cell.value}
                      </Text>
                    );
                  }
                  return (
                    <Td
                      {...cell.getCellProps()}
                      key={index}
                      fontSize={{ sm: "14px" }}
                      minW={{ sm: "150px", md: "200px", lg: "auto" }}
                      borderColor="transparent"
                    >
                      {data}
                    </Td>
                  );
                })}
              </Tr>
            );
          })}
        </Tbody>
      </Table>
      {selectedRows.length > 0 && (
        <Button
          onClick={deleteSelectedRows}
          colorScheme="red"
          mt="4"
          size="sm"
        >
          삭제
        </Button>
      )}
      </Flex>
    </Card>
  );
}
