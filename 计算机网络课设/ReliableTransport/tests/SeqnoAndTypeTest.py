from tests.BasicTest import *

from tests.BasicTest import BasicTest

"""
A base class for tests that need to touch packets based on their, er, seqno and type.
"""

class AlwaysContains():
    """ Utility class. """
    def __contains__(self, item):
        return True

class SeqnoAndTypeTest(BasicTest):
    def __init__(self, name, forwarder, input_file, seqnos = [], types = [], multiplicity = 1, sackMode = False):
        super(SeqnoAndTypeTest, self).__init__(name, forwarder, input_file, sackMode = sackMode)
        self.seqnos = seqnos
        self.seen_seqnos = {}
        self.types = types
        self.multiplicity = multiplicity

    def should_touch(self, packet, debug=False):
        seen_enough = (packet.seqno in self.seen_seqnos and
                        self.seen_seqnos[packet.seqno] >= self.multiplicity)
        result = (not packet.bogon and
            packet.seqno in self.seqnos and
            packet.msg_type in self.types and
            not seen_enough)

        if debug:
            print ("%s: %s, %s, %s, %s" % (result, not packet.bogon,
                packet.seqno in self.seqnos,
                not seen_enough,
                packet.msg_type in self.types))
        if result:
            if packet.seqno in self.seen_seqnos:
                self.seen_seqnos[packet.seqno] += 1
            else:
                self.seen_seqnos[packet.seqno] = 1
        return result

    def handle_packet(self):
        for p in self.forwarder.in_queue:
            if not self.should_touch(p):
                self.forwarder.out_queue.append(p)
        self.forwarder.in_queue = []

