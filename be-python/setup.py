from setuptools import setup, find_packages
import yaml

with open('package_data.yml','r') as package_data_file:
    package_data = yaml.load(package_data_file, yaml.SafeLoader)

setup(
    name='PyBookworm',
    version=f"{package_data.get('version')}",
    url='',
    author='',
    author_email='',
    description='',
    packages=find_packages(),    
    install_requires=[],
)
