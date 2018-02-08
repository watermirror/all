#!/usr/bin/env python

# A tool for looking up words in different languages.
# @author zheng.che
# @version 18.2.8.0

import sys
from pyquery import PyQuery
from urllib import quote

# The source URL for looking up.
SOURCE_URL = 'http://www.youdao.com/w/eng/%s'

# Errors.
__ERROR_HEADER = '[error] %s'
ERROR_UNKNOWN = "Something terrible happened, you should debug this program."
ERROR_NO_QUERY_WORD = 'You must provide a word for looking up.'
ERROR_WRONG_QUERY_WORD = 'You may change another word.'

# Global vars.
g_errorOccurred = False
g_queryWord = ''
g_soundMark = ''
g_translations = ''

# Alert an error and exit program.
def throwError(error) :
    global g_errorOccurred
    if g_errorOccurred is False :
        print __ERROR_HEADER % error
        g_errorOccurred = True
    exit(1)

# Get query word from command line arguments.
def getQueryWord() :
    if len(sys.argv) < 2 :
        throwError(ERROR_NO_QUERY_WORD)
    return quote(sys.argv[1].encode('utf8'))

def handleSourceError(document) :
    errorTypo = document('.error-wrapper').eq(0)('.error-typo')
    if errorTypo.html() is None :
        return True

    print errorTypo('h4').eq(0).text()
    for suggestion in errorTypo('.typo-rel').items() :
        print suggestion.text()
    return False

def doMain() :
    global g_queryWord
    global g_soundMark
    global g_translations

    document = PyQuery(SOURCE_URL % getQueryWord())
    wordBookDiv = document('#phrsListTab')('.wordbook-js').eq(0)
    transContainerDiv = document('#phrsListTab')('.trans-container').eq(0)

    if handleSourceError(document) is not True :
        throwError(ERROR_WRONG_QUERY_WORD)

    g_queryWord = wordBookDiv('.keyword').eq(0).text()

    pronounceDivs = wordBookDiv('.baav')
    isFirstMark = True
    for pronounceDiv in pronounceDivs('.pronounce').items() :
        if not isFirstMark :
            g_soundMark += '; '
        else :
            isFirstMark = False
        g_soundMark += pronounceDiv.text()

    isFirstTrans = True
    for li in transContainerDiv('ul').eq(0)('li').items() :
        if not isFirstTrans :
            g_translations += '\n'
        else :
            isFirstTrans = False
        g_translations += '- %s' % li.text()

    print '**%s**' % g_queryWord
    print g_soundMark
    print g_translations

if __name__ == '__main__' :
    try :
        doMain()
    except :
        throwError(ERROR_UNKNOWN)
