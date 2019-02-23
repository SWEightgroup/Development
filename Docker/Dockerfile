FROM ubuntu:18.04

RUN apt-get update -q && \
    apt-get install -y locales apt-utils build-essential automake autoconf libtool && \
    apt-get install -y libicu-dev cmake libboost-regex-dev libboost-filesystem-dev \
                       libboost-program-options-dev wget libboost-thread-dev \
                       zlib1g-dev swig 
RUN locale-gen en_US.UTF-8 
ENV FLINSTALL /usr/local
WORKDIR ${FLINSTALL}
RUN wget https://github.com/TALP-UPC/FreeLing/releases/download/4.1/FreeLing-4.1.tar.gz -q --show-progress && \
    tar -xzf FreeLing-4.1.tar.gz && \
	rm -rf FreeLing-4.1.tar.gz
RUN ls
#ADD FreeLing-4.1.tar.gz ${FLINSTALL}
RUN ls 
WORKDIR /usr/local/FreeLing-4.1/build
RUN ls
RUN cmake .. && make -j 4 install
RUN apt-get --purge -y remove build-essential \
    automake autoconf libtool cmake && \
    apt-get autoremove -y && \
    apt-get clean -y && \
    rm -rf /var/lib/apt/lists/*
ENV LD_LIBRARY_PATH /usr/local/lib:${LD_LIBRARY_PATH}
ENV FREELINGDIR ${FLINSTALL}
#ARG primary_lang
#ARG secondary_lang
#ls -d /usr/local/share/freeling/?? | grep -v ${lang} | xargs rm -rf 
#RUN ls -d /usr/local/share/freeling/??
#EXPOSE 50005/tcp
#EXPOSE 50005/udp
#ENTRYPOINT [ "/usr/local/bin/analyze", "-f", "it.cfg", "--server" "--port", "50005", "&" ] 
