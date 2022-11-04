#!/usr/bin/env python
import requests
from bs4 import BeautifulSoup
import sys

URL = "https://www.skyss.no/avvik/"
USE_LOCAL = False
CREATE_LOCAL = False


def create_local_file(content):
    with open("local.html", "w") as f:
        f.write(str(content))
        print("saved to file")


def get_file_content():
    if USE_LOCAL:
        with open("local.html", "r") as f:
            content = BeautifulSoup(f, "html.parser")
    else:
        res = requests.get(URL)
        html_body = res.text
        content = BeautifulSoup(html_body, "html.parser")

    return content


def get_affected_routes(deviations):
    affected_routes = {}
    for d in deviations:
        routes = d.find(class_="accordion__route-numbers").text
        message = str(d.p.text)

        for route in routes.split(","):
            route = route.strip()
            affected_routes[route] = message
    return affected_routes


def main():
    doc = get_file_content()

    if CREATE_LOCAL:
        create_local_file(doc)

    deviations = doc.find_all(class_="accordion--driftsmelding")
    affected_routes = get_affected_routes(deviations)

    user_args = sys.argv[1:]
    for arg in user_args:
        if arg in affected_routes.keys():
            print(f"Deviation in route {arg}:")
            print(affected_routes[str(arg)] + "\n" + ("-" * 10))


if __name__ == "__main__":
    main()
