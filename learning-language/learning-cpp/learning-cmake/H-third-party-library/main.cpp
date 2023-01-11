/**
 * Created by xjj on 2023/1/11.
 */
#include <iostream>
// /usr/local/include/boost
#include <boost/shared_ptr.hpp>
#include <boost/filesystem.hpp>

int main(int argc, char *argv[]) {
    std::cout << "Hello Third Party Include!" << std::endl;
    boost::shared_ptr<int> isp(new int(4));

    boost::filesystem::path path = "/usr/local/Cellar/cmake";
    if (path.is_relative()) {
        std::cout << "Path is relative" << std::endl;
    } else {
        std::cout << "Path is not relative" << std::endl;
    }
    return 0;
}